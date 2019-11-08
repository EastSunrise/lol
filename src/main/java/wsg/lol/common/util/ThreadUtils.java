package wsg.lol.common.util;

/**
 * 线程工具类
 *
 * @author wangsigen
 * @date 2019/11/8
 * @since 1.0
 */
public class ThreadUtils {

    /**
     * 暂停线程等待
     */
    public static void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
