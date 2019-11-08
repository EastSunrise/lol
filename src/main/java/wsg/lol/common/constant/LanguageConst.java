package wsg.lol.common.constant;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Locale;

/**
 * 语言枚举
 *
 * @author wangsigen
 * @date 2019/11/7
 * @see <a href="https://ddragon.leagueoflegends.com/cdn/languages.json">languages.json</a>
 * @since 1.0
 */
public class LanguageConst {
    private static final Locale[] LANGUAGES = {
            Locale.US, Locale.UK, Locale.GERMANY, Locale.ITALY, Locale.FRANCE, Locale.JAPAN, Locale.KOREA, Locale.CHINA, Locale.TAIWAN
    };

    public static boolean isSupported(Locale locale) {
        return ArrayUtils.contains(LANGUAGES, locale);
    }

    /**
     * 判断是否支持该语言
     *
     * @param code eg.en_GB
     */
    public static boolean isSupported(String code) {
        if (code == null) {
            throw new NullPointerException();
        }
        String[] parts = code.split("_");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Irregular code: " + code);
        }
        return ArrayUtils.contains(LANGUAGES, new Locale(parts[0], parts[1]));
    }

    public static String getCode(Locale locale) {
        if (!isSupported(locale)) {
            throw new IllegalArgumentException("Unsupported country: " + locale.getCountry());
        }
        return locale.getLanguage() + "_" + locale.getCountry();
    }
}
