package online.nwen.service.security;

import online.nwen.service.api.IJWTService;
import online.nwen.service.api.exception.SecurityException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Before execute a security method, the security context must verified.
 */
@Aspect
@Component
public class SecurityContextVerificationAspect {
    private static final Logger logger = LoggerFactory.getLogger(SecurityContextVerificationAspect.class);
    private IJWTService securityContextService;

    public SecurityContextVerificationAspect(IJWTService securityContextService) {
        this.securityContextService = securityContextService;
    }

    @Pointcut("@annotation(Security)")
    public void securityMethod() {
    }

    /**
     * Check the security context with JWT token, if fail throw {@link SecurityException}
     *
     * @param joinPoint The join point.
     */
    @Before(value = "securityMethod()")
    public void checkSecurityContext(JoinPoint joinPoint) {
        String methodSignature = joinPoint.getSignature().toLongString();
        logger.debug("Start to check security context for method: {}", methodSignature);
        if (SecurityContextHolder.INSTANCE.getContext() == null) {
            logger.error("Fail to get security context when execute method: {}", methodSignature);
            throw new SecurityException(SecurityException.Code.SECURITY_ERROR_CONTEXT_NOT_FOUND);
        }
        if (SecurityContextHolder.INSTANCE.getContext().getJwtToken() == null) {
            logger.error("Fail to get jwt token from security context when execute method: {}", methodSignature);
            throw new SecurityException(SecurityException.Code.SECURITY_ERROR_JWT_TOKEN_NOT_FOUND);
        }
        this.securityContextService.verify(SecurityContextHolder.INSTANCE.getContext().getJwtToken());
        logger.debug("Success to check security context for method: {}", methodSignature);
    }
}
