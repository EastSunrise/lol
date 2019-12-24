package wsg.lol.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;

/**
 * Base test.
 *
 * @author Kingen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseTest implements InitializingBean {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Override
    public void afterPropertiesSet() {
    }

    protected void testVersion(VoidTask<String> task) {
        task.run("9.22.1");
        task.run("9.23.1");
        exception.expect(AppException.class);
        exception.expectMessage(ErrorCodeConst.DRAGON_FILE_IO_ERROR);
        task.run("9.24.1");
    }

    protected interface Task<T> {
        Object run(T t);
    }

    protected interface NoneArgsTask {
        Object run();
    }

    protected interface VoidTask<T> {
        void run(T t);
    }

    protected interface NoneTask {
        void run();
    }
}
