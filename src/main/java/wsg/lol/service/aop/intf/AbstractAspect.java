package wsg.lol.service.aop.intf;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import wsg.lol.common.utils.LogUtil;

/**
 * wsg
 *
 * @author wangsigen
 */
public abstract class AbstractAspect {

    /**
     * 前置通知
     * <p>
     * This method is called before the target method is called.
     * <p>
     * annotation @Before(value = "")
     */
    void doBefore(JoinPoint joinPoint) {
        LogUtil.info("Do before calling " + joinPoint.getSignature().getName());
    }

    /**
     * 后置返回通知
     * <p>
     * This method is called just when the return value of the target method is instance the type of the @param
     * returnValue.
     * <p>
     * annotation @AfterReturning(value = "", returning = "returnValue")
     */
    void doAfterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
        LogUtil.info("Do after " + joinPoint.getSignature().getName() + " return " + returnValue);
    }

    /**
     * 后置异常通知
     * <p>
     * This method is called just when the thrown exception of the target method is instance of the type of @param
     * throwable.
     * <p>
     * annotation @AfterThrowing(value = "", throwing = "throwable")
     */
    void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        LogUtil.info("Do after " + joinPoint.getSignature().getName() + " throw " + throwable);
    }

    /**
     * 后置最终通知
     * <p>
     * This method is called after the target method is called.
     * <p>
     * annotation @After(value = "")
     */
    void doAfterAdvice(JoinPoint joinPoint) {
        LogUtil.info("Do after calling " + joinPoint.getSignature().getName());
    }

    /**
     * 环绕通知
     * <p>
     * This method is called to do things as follows:
     * Whether and when the target method will be called.
     * Replace the arguments of the method when called or the return value after being called if necessary.
     * <p>
     * The first argument of the method must be org.aspectj.lang .ProceedingJoinPoint.
     * <p>
     * annotation @Around(value = "")
     */
    Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        LogUtil.info("Do when calling " + proceedingJoinPoint.getSignature().getName());
        return null;
    }
}
