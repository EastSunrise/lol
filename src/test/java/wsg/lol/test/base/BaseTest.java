package wsg.lol.test.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
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

    protected void testVersion(Task<String> task) {
        Assert.assertTrue(task.run("9.22.1").isSuccess());
        Assert.assertTrue(task.run("9.23.1").isSuccess());
        exception.expect(AppException.class);
        exception.expectMessage(ErrorCodeConst.DRAGON_FILE_IO_ERROR);
        Assert.assertFalse(task.run("9.24.1").isSuccess());
    }

    protected interface Task<T> {
        Result run(T t);
    }
}
