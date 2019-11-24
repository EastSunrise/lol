package wsg.lol.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for recording the performance of services.
 *
 * @author Kingen
 */
@Aspect
@Component
public class PerformanceAspect {

    @Pointcut("@annotation(wsg.lol.common.annotation.Performance)")
    private void performance() {}

    @Around("performance()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long time = System.currentTimeMillis() - start;
        logger.info("{}.{} cost {} ms.", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), time);
        return result;
    }
}
