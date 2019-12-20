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
 * Aspect to change the region.
 *
 * @author Kingen
 */
@Aspect
@Component
public class RegionAspect {

    private static final Logger logger = LoggerFactory.getLogger(RegionAspect.class);

    @Pointcut("@annotation(wsg.lol.common.annotation.Platform)")
    private void region() {}

    @Around(value = "region() && @annotation(source)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Platform source) throws Throwable {
        RegionEnum region = source.platform();
        RegionEnum from = RegionIdentifier.getRegion();
        logger.info("Switching the region from {} to {}...", from, region);
        RegionIdentifier.setPlatform(region);
        Object result = joinPoint.proceed();
        RegionIdentifier.setPlatform(from);
        logger.info("Switch the region back from {} to {}.", region, from);
        return result;
    }
}
