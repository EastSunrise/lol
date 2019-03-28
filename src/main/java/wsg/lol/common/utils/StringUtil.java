package wsg.lol.common.utils;

/**
 * wsg
 *
 * @author wangsigen
 */
public class StringUtil {

    public static final String CELL_SEPARATOR = ",";

    public static String join(Object[] args, String separator) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                builder.append(separator);
            }
            builder.append(args[i]);
        }
        return builder.toString();
    }
}
