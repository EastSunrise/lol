package wsg.lol.common.util;

import wsg.lol.common.pojo.base.BaseEnum;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class EnumUtils {

    /**
     * parse enum from code
     */
    public static <V, E extends Enum & BaseEnum<V>> E parseFromValue(V v, Class<E> type) {
        E[] enums = type.getEnumConstants();
        for (E e : enums) {
            if (e.getValue().equals(v)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Unknown type: " + type + " with value: " + v);
    }
}
