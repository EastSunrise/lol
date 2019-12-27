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

    private static final Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);

    @Pointcut("@annotation(wsg.lol.service.aop.Performance) || @within(wsg.lol.service.aop.Performance)")
    private void performance() {}

    @Around("performance()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - start;
        logger.info("{}.{} cost {} ms.", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), time);
        return result;
    }
}
