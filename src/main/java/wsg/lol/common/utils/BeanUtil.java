package wsg.lol.common.utils;

import com.alibaba.fastjson.JSONObject;
import wsg.lol.common.annotation.JsonKey;
import wsg.lol.common.annotation.Label;
import wsg.lol.common.annotation.Optional;
import wsg.lol.pojo.base.BaseDmo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-01 13:52
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

    public static <T extends BaseDmo> void parseFromJSONObject(T target, JSONObject jsonObject) {
        Class clazz = target.getClass();
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(JsonKey.class)) {
                    String[] levels = field.getName().split("_");
                    int len = levels.length;
                    JSONObject curObject = jsonObject;
                    for (int i = 0; i < len - 1; i++) {
                        if (curObject.containsKey(levels[i]))
                            curObject = curObject.getJSONObject(levels[i]);
                    }
                    if (curObject.containsKey(levels[len - 1])) {
                        Object value = curObject.getObject(levels[len - 1], field.getType());
                        field.set(target, value);
                    }
                } else if (field.isAnnotationPresent(Label.class)) {
                    if (jsonObject.containsKey(field.getName()))
                        field.set(target, StringUtil.join(jsonObject.getJSONArray(field.getName()).toArray(),
                                StringUtil.CELL_SEPARATOR));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
