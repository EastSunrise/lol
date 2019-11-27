package wsg.lol.common.pojo.transfer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import wsg.lol.common.annotation.Flatten;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.base.BaseDto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * // TODO: (Kingen, 2019/11/27) *
 *
 * @author Kingen
 */
public class ObjectTransfer {

    private static final Class<?>[] SIMPLE_CLASSES = new Class[]{

    };

    public static <T extends BaseDo> List<T> transferList(List<? extends BaseDto> fs, Class<T> clazz) {
        if (CollectionUtils.isEmpty(fs)) {
            return null;
        }

        List<T> ts = new ArrayList<>();
        for (BaseDto f : fs) {
            T t = ObjectTransfer.transfer(f, clazz);
            ts.add(t);
        }
        return ts;
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseDo> T transfer(BaseDto f, Class<T> clazz) {
        if (f == null) {
            return null;
        }
        Class<? extends BaseDto> fClass = f.getClass();
        Field[] fFields = fClass.getDeclaredFields();
        Map<String, Object> fMap = new HashMap<>();
        T t = null;
        try {
            for (Field fField : fFields) {
                fField.setAccessible(true);
                fMap.put(fField.getName(), fField.get(f));
            }

            t = clazz.newInstance();
            for (Field tField : clazz.getDeclaredFields()) {
                tField.setAccessible(true);
                String name = tField.getName();
                if (tField.isAnnotationPresent(Flatten.class)) {
                    String[] parts = StringUtils.splitByCharacterTypeCamelCase(name);
                    Map<String, Object> map = fMap;
                    Class<? extends BaseDto> innerClass;
                    for (int i = 0; i < parts.length; i++) {
                        Object innerObject = map.get(parts[i]);
                        if (innerObject == null) {
                            break;
                        }
                        if (i == parts.length - 1) {
                            tField.set(t, innerObject);
                            break;
                        }
                        if (innerObject instanceof BaseDto) {
                            map = new HashMap<>();
                            innerClass = ((BaseDto) innerObject).getClass();
                            Field[] innerFields = innerClass.getDeclaredFields();
                            for (Field innerField : innerFields) {
                                innerField.setAccessible(true);
                                map.put(innerField.getName(), innerField.get(innerObject));
                            }
                            continue;
                        }
                        if (innerObject instanceof Map) {
                            map = (Map<String, Object>) innerObject;
                        }
                    }

                    continue;
                }
                tField.set(t, fMap.get(name));
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return t;
    }
}
