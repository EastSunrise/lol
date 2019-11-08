package wsg.lol.controller.real;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import wsg.lol.service.real.intf.RealService;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Service
@Lazy()
public class RealTask implements InitializingBean {

    private RealService realService;

    @Override
    public void afterPropertiesSet() {
        new Thread(() -> {
            while (true) {
                realService.updateSummoners();
            }
        }, "Update summoner").start();
        new Thread(() -> {
            while (true) {
                realService.extendLib();
            }
        }, "Extend lib").start();
    }

    @Autowired
    public void setRealService(RealService realService) {
        this.realService = realService;
    }
}
