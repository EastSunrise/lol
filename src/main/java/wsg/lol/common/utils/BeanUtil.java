package wsg.lol.common.utils;

import wsg.lol.pojo.annotation.Optional;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * wsg
 *
 * @author wangsigen
 */
public class BeanUtil {

    public static Map<String, Object> getQueryParams(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> params = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Optional.class)) {
                String name = field.getName();
                Object value = null;
                try {
                    value = field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value != null)
                    params.put(name, value);
            }
        }
        return params;
    }
}
