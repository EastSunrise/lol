package wsg.lol.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.config.RegionIdentifier;

/**
 * Aspect to change the datasource.
 *
 * @author Kingen
 */
@Aspect
@Component
public class DatasourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DatasourceAspect.class);

    @Pointcut("@annotation(wsg.lol.common.annotation.Platform)")
    private void datasource() {}

    @Around(value = "datasource() && @annotation(source)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Platform source) {
        RegionEnum region = source.platform();
        RegionEnum from = RegionIdentifier.getRegion();
        logger.info("Switching the datasource from {} to {}...", from, region);
        RegionIdentifier.setPlatform(region);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        RegionIdentifier.setPlatform(from);
        logger.info("Switch the datasource back from {} to {}.", region, from);
        return result;
    }
}
