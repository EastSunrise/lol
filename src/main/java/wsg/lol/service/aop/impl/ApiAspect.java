package wsg.lol.service.aop.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * wsg
 *
 * @author wangsigen
 */
@Aspect
@Component
public class ApiAspect {

    @Pointcut(value = "@annotation(wsg.lol.common.annotation.AccessApi)")
    public void api() {
    }

    @Around(value = "api()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
