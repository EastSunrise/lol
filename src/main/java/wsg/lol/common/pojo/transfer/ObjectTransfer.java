package wsg.lol.common.pojo.transfer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import wsg.lol.common.annotation.Flatten;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.constant.ErrorCodeConst;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Transfer between DTO and DO by the same name of field.
 *
 * @author Kingen
 */
public class ObjectTransfer {

    public static <F extends BaseDto, T extends BaseDo> List<T> transferDtoList(List<F> fs, Class<T> tClass) {
        if (CollectionUtils.isEmpty(fs)) {
            return new ArrayList<>();
        }

        List<Field> fFields = FieldUtils.getAllFieldsList(fs.get(0).getClass());
        try {
            // map of all fields from the f class.
            Map<String, List<Object>> fMap = new HashMap<>();
            for (Field fField : fFields) {
                fField.setAccessible(true);
                List<Object> values = new LinkedList<>();
                for (F f : fs) {
                    values.add(fField.get(f));
                }
                fMap.put(fField.getName(), values);
            }

            List<T> ts = new LinkedList<>();
            for (int i = 0; i < fs.size(); i++) {
                ts.add(tClass.newInstance());
            }
            for (Field tField : FieldUtils.getAllFieldsList(tClass)) {
                tField.setAccessible(true);
                String name = tField.getName();

                // flatten fields.
                if (tField.isAnnotationPresent(Flatten.class)) {
                    String[] parts = StringUtils.splitByCharacterTypeCamelCase(name);
                    Iterator<T> iterator = ts.iterator();
                    for (F f : fs) {
                        Object value = f;
                        for (String part : parts) {
                            value = FieldUtils.readField(value, part.toLowerCase(), true);
                            if (value == null) {
                                break;
                            }
                        }
                        tField.set(iterator.next(), value);
                    }
                    continue;
                }

                // common fields.
                List<Object> values = fMap.get(name);
                if (values == null) {
                    continue;
                }
                Iterator<T> iterator = ts.iterator();
                for (Object value : values) {
                    tField.set(iterator.next(), value);
                }
            }
            return ts;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static <F extends BaseDto, T extends BaseDo> T transferDto(F f, Class<T> tClass) {
        if (f == null) {
            return null;
        }
        List<Field> fFields = FieldUtils.getAllFieldsList(f.getClass());
        try {
            // map of all fields from the f class.
            Map<String, Object> fMap = new HashMap<>();
            for (Field fField : fFields) {
                fField.setAccessible(true);
                fMap.put(fField.getName(), fField.get(f));
            }

            T t = tClass.newInstance();
            for (Field tField : FieldUtils.getAllFieldsList(tClass)) {
                tField.setAccessible(true);
                String name = tField.getName();

                // flatten fields.
                if (tField.isAnnotationPresent(Flatten.class)) {
                    String[] parts = StringUtils.splitByCharacterTypeCamelCase(name);
                    Object value = f;
                    for (String part : parts) {
                        value = FieldUtils.readField(value, part.toLowerCase(), true);
                        if (value == null) {
                            break;
                        }
                    }
                    tField.set(t, value);
                    continue;
                }

                // common fields.
                tField.set(t, fMap.get(name));
            }
            return t;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
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

        List<Field> fFields = FieldUtils.getAllFieldsList(f.getClass());
        try {
            Map<String, Object> fMap = new HashMap<>();
            for (Field fField : fFields) {
                fField.setAccessible(true);
                String name = fField.getName();
                Object value = fField.get(f);
                if (fField.isAnnotationPresent(Flatten.class)) {
                    String[] parts = StringUtils.splitByCharacterTypeCamelCase(name);
                    if (parts == null || parts.length != 2) {
                        throw new AppException(ErrorCodeConst.ILLEGAL_ARGS, "Wrong Flatten annotation on " + f.getClass() + "." + name);
                    }
                    if (!fMap.containsKey(parts[0])) {
                        fMap.put(parts[0], new HashMap<String, Object>());
                    }
                    ((Map<String, Object>) fMap.get(parts[0])).put(parts[1], value);
                    continue;
                }
                fMap.put(name, value);
            }

            T t = tClass.newInstance();
            for (Field tField : FieldUtils.getAllFieldsList(tClass)) {
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
            return t;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
