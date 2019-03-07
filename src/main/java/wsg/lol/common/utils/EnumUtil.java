package wsg.lol.common.utils;

import wsg.lol.common.enums.intf.CodeEnum;
import wsg.lol.common.enums.intf.IdEnum;

import java.lang.reflect.Field;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-05 10:40
 */
public class EnumUtil {

    /**
     * parse enum from code
     */
    public static <T extends CodeEnum> T parseFromCode(String code, Class<T> type) {
        T[] enums = type.getEnumConstants();
        for (T e : enums) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Unknown type: " + type + " with code: " + code);
    }

    /**
     * parse enum from id
     */
    public static <T extends IdEnum> T parseFromId(int id, Class<T> type) {
        T[] enums = type.getEnumConstants();
        for (T e : enums) {
            if (e.getId() == id) {
                return e;
            }
        }

        throw new IllegalArgumentException("Unknown type: " + type + " with id: " + id);
    }

    public static <T extends Enum> T parseFromField(String fieldName, Object fieldValue, Class<T> type) {
        T[] enums = type.getEnumConstants();
        for (T t : enums) {
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (field.getName().equals(fieldName) && field.get(t).equals(fieldValue))
                        return t;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new IllegalArgumentException("Unknown type: " + type + " with " + fieldName + ": " + fieldValue);
    }
}
