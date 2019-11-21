package wsg.lol.common.util;

import wsg.lol.common.base.BaseEnum;

/**
 * todo
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

    public static <T extends Enum<T>> T parseEnumByOrdinal(int ordinal, Class<T> clazz) {
        T[] enums = clazz.getEnumConstants();
        if (ordinal < 0 || ordinal > enums.length) {
            throw new IndexOutOfBoundsException("Unknown type: " + clazz + " with ordinal: " + ordinal);
        }
        return enums[ordinal];
    }
}
