package wsg.lol.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.enums.system.PlatformRoutingEnum;
import wsg.lol.dao.mybatis.config.DatabaseIdentifier;

/**
 * Aspect to change the datasource.
 *
 * @author Kingen
 */
@Aspect
@Component
public class DatasourceAspect {

    @Pointcut("@annotation(wsg.lol.common.annotation.Platform)")
    private void datasource() {}

    @Around(value = "datasource() && @annotation(source)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Platform source) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        PlatformRoutingEnum platform = source.platform();
        PlatformRoutingEnum from = DatabaseIdentifier.getPlatform();
        logger.info("Switching the datasource from {} to {}...", from, platform);
        DatabaseIdentifier.setPlatform(platform);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        DatabaseIdentifier.setPlatform(null);
        return result;
    }
}
