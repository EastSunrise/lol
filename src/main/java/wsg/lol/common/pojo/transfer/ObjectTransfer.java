package wsg.lol.common.pojo.transfer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import wsg.lol.common.annotation.Flatten;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.constant.ErrorCodeConst;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Transfer between DTO and DO by the same name of field.
 *
 * @author Kingen
 */
public class ObjectTransfer {

    private static final Class<?>[] SIMPLE_CLASSES = new Class[]{

    };

    public static <F extends BaseDto, T extends BaseDo> List<T> transferDtoList(List<F> fs, Class<T> tClass) {
        return ObjectTransfer.transferDtoList(fs, null, tClass);
    }

    public static <F extends BaseDto, T extends BaseDo> List<T> transferDtoList(List<F> fs, Class<F> fClass, Class<T> tClass) {
        if (CollectionUtils.isEmpty(fs)) {
            return new ArrayList<>();
        }

        List<T> ts = new ArrayList<>();
        for (F f : fs) {
            T t = ObjectTransfer.transferDto(f, fClass, tClass);
            ts.add(t);
        }
        return ts;
    }

    public static <F extends BaseDto, T extends BaseDo> T transferDto(F f, Class<T> tClass) {
        return transferDto(f, null, tClass);
    }

    @SuppressWarnings("unchecked")
    public static <F extends BaseDto, T extends BaseDo> T transferDto(F f, Class<F> fClass, Class<T> tClass) {
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

    public static <F extends BaseDo, T extends BaseDto> List<T> transferDoList(List<F> fs, Class<T> tClass) {
        if (CollectionUtils.isEmpty(fs)) {
            return new ArrayList<>();
        }

        List<T> ts = new ArrayList<>();
        for (F f : fs) {
            T t = ObjectTransfer.transferDo(f, tClass);
            ts.add(t);
        }
        return ts;
    }

    @SuppressWarnings("unchecked")
    public static <F extends BaseDo, T extends BaseDto> T transferDo(F f, Class<T> tClass) {
        if (f == null) {
            return null;
        }

        Class<F> fClass = (Class<F>) f.getClass();
        Field[] fFields = fClass.getDeclaredFields();
        Map<String, Object> fMap = new HashMap<>();
        T t = null;
        try {
            for (Field fField : fFields) {
                fField.setAccessible(true);
                String name = fField.getName();
                Object value = fField.get(f);
                if (fField.isAnnotationPresent(Flatten.class)) {
                    String[] parts = StringUtils.splitByCharacterTypeCamelCase(name);
                    if (parts == null || parts.length != 2) {
                        throw new AppException(ErrorCodeConst.ILLEGAL_ARGS, "Wrong Flatten annotation on " + fClass + "." + name);
                    }
                    if (!fMap.containsKey(parts[0])) {
                        fMap.put(parts[0], new HashMap<String, Object>());
                    }
                    ((Map<String, Object>) fMap.get(parts[0])).put(parts[1], value);
                    continue;
                }
                fMap.put(name, value);
            }

            t = tClass.newInstance();
            for (Field tField : tClass.getDeclaredFields()) {
                tField.setAccessible(true);
                String name = tField.getName();
                Class<?> pClass = tField.getType();
                Object value = fMap.get(name);
                if (value instanceof Map && ClassUtils.isAssignable(pClass, BaseDto.class)) {
                    Object pValue = pClass.newInstance();
                    Map<String, Object> pMap = (Map<String, Object>) value;
                    for (Field pField : pClass.getDeclaredFields()) {
                        pField.setAccessible(true);
                        pField.set(pValue, pMap.get(pField.getName()));
                    }
                    tField.set(t, pValue);
                    continue;
                }
                tField.set(t, value);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return t;
    }
}
