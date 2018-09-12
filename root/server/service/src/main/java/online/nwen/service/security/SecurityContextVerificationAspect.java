package online.nwen.service.security;

import online.nwen.service.api.exception.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SecurityContextVerificationAspect {
    @Around("@annotation(Security) && execution(public * *(..))")
    public Object checkSecurityContext(final ProceedingJoinPoint proceedingJoinPoint)throws ServiceException {

        Object result=null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {

            throw throwable;
        } finally {
            long duration = System.currentTimeMillis() - start;

            log.info(
                    "{}.{} took {} ms",
                    proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName(),
                    proceedingJoinPoint.getSignature().getName(),
                    duration);
        }

        return value;
    }

}
