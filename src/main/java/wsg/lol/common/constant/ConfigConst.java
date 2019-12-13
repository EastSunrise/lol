package wsg.lol.common.constant;

import org.apache.commons.lang3.time.DateUtils;
import wsg.lol.common.base.AppException;

import java.text.ParseException;
import java.util.Date;

/**
 * Some configured constant arguments
 *
 * @author Kingen
 */
public class ConfigConst {

    /**
     * Current version of the static data.
     */
    public static final String CURRENT_VERSION = "CURRENT_VERSION";

    /**
     * Shared status.
     */
    public static final String SHARED_STATUS = "SHARED_STATUS";

    /**
     * Rotation of champions.
     */
    public static final String CHAMPION_ROTATION = "CHAMPION_ROTATION";

    /**
     * Flag marked if the database has been initialize.
     */
    public static final String IS_DATABASE_INITIALIZED = "IS_DATABASE_INITIALIZED";

    public static final Date LAST_MATCH_ERROR_DATE = getLastMatchErrorDate();

    public static final Date LAST_UPDATE_ERROR_DATE = getLastUpdateErrorDate();

    public static final Date MATCH_BEGIN_DATE = getMatchBeginDate();

    public static Date getMatchBeginDate() {
        try {
            return DateUtils.parseDate("2019-11-21 00:00:00", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR);
        }
    }

    private static Date getLastMatchErrorDate() {
        try {
            return DateUtils.parseDate("2038-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR);
        }
    }

    private static Date getLastUpdateErrorDate() {
        try {
            return DateUtils.parseDate("2037-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR);
        }
    }
}
