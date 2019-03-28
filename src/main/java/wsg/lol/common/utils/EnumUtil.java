package wsg.lol.common.utils;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 */
public class EnumUtil {

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
