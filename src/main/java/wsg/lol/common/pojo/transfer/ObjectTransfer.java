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
 * Transfer DTO to DO with the same name of field.
 *
 * @author Kingen
 */
public class ObjectTransfer {

    private static final Class<?>[] SIMPLE_CLASSES = new Class[]{

    };

    public static <T extends BaseDo, F extends BaseDto> List<T> transferList(List<F> fs, Class<T> tClass) {
        return transferList(fs, null, tClass);
    }

    public static <T extends BaseDo, F extends BaseDto> List<T> transferList(List<F> fs, Class<F> fClass, Class<T> tClass) {
        if (CollectionUtils.isEmpty(fs)) {
            return new ArrayList<>();
        }

        List<T> ts = new ArrayList<>();
        for (F f : fs) {
            T t = ObjectTransfer.transfer(f, fClass, tClass);
            ts.add(t);
        }
        return ts;
    }

    public static <T extends BaseDo, F extends BaseDto> T transfer(F f, Class<T> tClass) {
        return transfer(f, null, tClass);
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseDo, F extends BaseDto> T transfer(F f, Class<F> fClass, Class<T> tClass) {
        if (f == null) {
            return null;
        }
        if (fClass == null) {
            fClass = (Class<F>) f.getClass();
        }
        // TODO: (Kingen, 2019/11/28)  fields of super class.
        Field[] fFields = fClass.getDeclaredFields();
        Map<String, Object> fMap = new HashMap<>();
        T t = null;
        try {
            for (Field fField : fFields) {
                fField.setAccessible(true);
                fMap.put(fField.getName(), fField.get(f));
            }

            t = tClass.newInstance();
            for (Field tField : tClass.getDeclaredFields()) {
                tField.setAccessible(true);
                String name = tField.getName();
                if (tField.isAnnotationPresent(Flatten.class)) {
                    String[] parts = StringUtils.splitByCharacterTypeCamelCase(name);
                    Map<String, Object> map = fMap;
                    Class<? extends BaseDto> innerClass;
                    for (int i = 0; i < parts.length; i++) {
                        Object innerObject = map.get(parts[i].toLowerCase());
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
