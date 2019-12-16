package wsg.lol.common.util;

import wsg.lol.common.pojo.serialize.JSONSerializable;

/**
 * Util for enums.
 *
 * @author Kingen
 */
public class EnumUtils {

    /**
     * Parse enum from {@link JSONSerializable#serialize()}
     */
    public static <T, E extends Enum<E> & JSONSerializable<T>> E parseFromObject(T value, Class<E> type) {
        E[] enums = type.getEnumConstants();
        for (E e : enums) {
            if (e.serialize().equals(value)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Unknown type: " + type + " with value: " + value);
    }

    /**
     * Parse enum from {@link Enum#ordinal()}
     */
    public static <T extends Enum<T>> T parseEnumByOrdinal(int ordinal, Class<T> clazz) {
        T[] enums = clazz.getEnumConstants();
        if (ordinal < 0 || ordinal > enums.length) {
            throw new IndexOutOfBoundsException("Unknown type: " + clazz + " with ordinal: " + ordinal);
        }
        return enums[ordinal];
    }
}
