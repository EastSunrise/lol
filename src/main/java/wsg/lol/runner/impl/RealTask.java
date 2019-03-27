package wsg.lol.runner.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import wsg.lol.service.real.intf.RealService;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
@Lazy()
public class RealTask implements InitializingBean {

    private RealService realService;

    @Override
    public void afterPropertiesSet() {
        new Thread(() -> {
            while (true) {
                realService.extendSummonerLibByMatch();
                realService.updateLeagues();
                realService.updateMastery();
            }
        }).start();
    }

    @Autowired
    public void setRealService(RealService realService) {
        this.realService = realService;
    }
}
