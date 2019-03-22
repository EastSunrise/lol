package wsg.lol.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-21 11:37
 */
public class DateUtil {

    public static final int ONE_MINUTE = 60 * 1000;
    public static final int ONE_HOUR = 60 * ONE_MINUTE;
    public static final int ONE_DAY = 24 * ONE_HOUR;

    public static Date getDate(String str, String format, String timeZone, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, locale);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
