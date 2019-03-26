package wsg.lol.runner.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import wsg.lol.service.real.intf.RealService;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
public class RealRunner implements ApplicationRunner {

    private RealService realService;

    @Override
    public void run(ApplicationArguments args) {
        realService.extendSummonerLibByMatch();
    }

    @Autowired
    public void setRealService(RealService realService) {
        this.realService = realService;
    }
}
