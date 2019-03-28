package wsg.lol.common.utils;

import java.util.Date;

/**
 * @author King
 */
public class LogUtil {

    public static void info(String message) {
        System.out.println(new Date() + "  " + message);
    }

    public static void addEvent(String message) {
        System.out.println(message);
    }
}
