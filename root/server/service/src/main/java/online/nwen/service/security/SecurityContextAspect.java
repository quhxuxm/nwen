package online.nwen.service.security;

import online.nwen.service.api.IJWTService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.SecurityException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Before execute a security method, the security context must verified.
 */
@Aspect
@Component
public class SecurityContextAspect {
    private static final Logger logger = LoggerFactory.getLogger(SecurityContextAspect.class);
    private IJWTService jwtService;

    public SecurityContextAspect(IJWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Pointcut("@annotation(online.nwen.service.security.annotation.Security)")
    private void securityMethod() {
    }

    @Pointcut("@annotation(online.nwen.service.security.annotation.PrepareSecurityContext)")
    private void prepareSecurityContext() {
    }

    /**
     * Check the security context with JWT token, if fail throw {@link SecurityException}
     *
     * @param joinPoint The join point.
     */
    @Before(value = "securityMethod()")
    @Order(1)
    public void checkSecurityContext(JoinPoint joinPoint) {
        String methodSignature = joinPoint.getSignature().toLongString();
        logger.debug("Start to check security context for method: {}", methodSignature);
        if (SecurityContextHolder.INSTANCE.getContext() == null) {
            logger.error("Fail to get security context when execute method: {}", methodSignature);
            throw new SecurityException(ExceptionCode.SECURITY_ERROR_CONTEXT_NOT_FOUND);
        }
        SecurityContext securityContextImpl = (SecurityContext) SecurityContextHolder.INSTANCE.getContext();
        try {
            this.jwtService.verify(securityContextImpl.getJwtToken());
        } catch (SecurityException e) {
            logger.error("Fail to verify the security context, clear the security context.");
            securityContextImpl.setAuthor(null);
            SecurityContextHolder.INSTANCE.clearContext();
            throw e;
        }
        logger.debug("Success to check security context for method: {}", methodSignature);
    }

    @Before(value = "prepareSecurityContext()")
    @Order(2)
    public void initSecurityContext(JoinPoint joinPoint) {
        String methodSignature = joinPoint.getSignature().toLongString();
        logger.debug("Start to refresh security context for method: {}", methodSignature);
        if (SecurityContextHolder.INSTANCE.getContext() == null) {
            logger.debug(
                    "Fail to refresh security context because of the security context not exist when execute method: {}",
                    methodSignature);
            return;
        }
        SecurityContext securityContextImpl = (SecurityContext) SecurityContextHolder.INSTANCE.getContext();
        if (securityContextImpl.getAuthor() == null) {
            if (securityContextImpl.getJwtToken() == null) {
                logger.debug(
                        "Fail to refresh security context because of the jwt token not exist when execute method: {}",
                        methodSignature);
                return;
            }
            securityContextImpl.setAuthor(this.jwtService.getAuthorFromJwtToken(securityContextImpl.getJwtToken()));
        }
        logger.debug("Success to refresh security context for method: {}", methodSignature);
    }
}
