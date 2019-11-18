package wsg.lol.common.util;

/**
 * Utility class for thread.
 *
 * @author Kingen
 */
public class ThreadUtils {

    /**
     * Thread sleeps for milliseconds.
     */
    public static void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
