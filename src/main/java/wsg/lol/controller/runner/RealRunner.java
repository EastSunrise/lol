package wsg.lol.controller.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * // TODO: (Kingen, 2019/11/21) *
 *
 * @author Kingen
 */
@Service
@Order(value = 2)
public class RealRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
