package wsg.lol.service.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.config.RegionIdentifier;
import wsg.lol.dao.api.client.ApiClient;

/**
 * Aspect for real-time scheduler.
 *
 * @author Kingen
 */
@Aspect
@Component
public class RealScheduledAspect {

    private static final Logger logger = LoggerFactory.getLogger(RealScheduledAspect.class);
    private ApiClient apiClient;

    @Pointcut("@annotation(wsg.lol.service.aop.RealScheduled) || @within(wsg.lol.service.aop.RealScheduled)")
    private void scheduled() {}

    @Around(value = "scheduled() && @annotation(realScheduled)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, RealScheduled realScheduled) throws Throwable {
        RegionEnum[] regions = realScheduled.regions();
        if (ArrayUtils.isEmpty(regions)) {
            regions = apiClient.getRegions().toArray(new RegionEnum[0]);
        }
        for (RegionEnum region : regions) {
            logger.info("Switch to region {}.", region);
            RegionIdentifier.setPlatform(region);
            joinPoint.proceed();
            RegionIdentifier.setPlatform(null);
        }
        return null;
    }

    @Autowired
    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
}
