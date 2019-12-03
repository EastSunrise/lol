package wsg.lol.common.pojo.serialize;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Type;

/**
 * Custom deserializer for enum type.
 *
 * @author Kingen
 */
public class CustomEnumDeserializer implements ObjectDeserializer {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object object = parser.parse(fieldName);
        if (type instanceof Class<?>) {
            Class<?> clazz = (Class<?>) type;
            if (ClassUtils.isAssignable(clazz, Enum.class)) {
                Class<? extends Enum> enumClass = (Class<? extends Enum>) type;
                if (ClassUtils.isAssignable(clazz, StringSerializable.class) && object instanceof String) {
                    String str = (String) object;
                    for (Enum enumConstant : enumClass.getEnumConstants()) {
                        StringSerializable serializable = (StringSerializable) enumConstant;
                        if (str.equals(serializable.serialize())) {
                            return (T) enumConstant;
                        }
                    }
                    if (ClassUtils.isAssignable(clazz, EqualsToSerializable.class)) {
                        for (Enum enumConstant : enumClass.getEnumConstants()) {
                            EqualsToSerializable equalsToSerializable = (EqualsToSerializable) enumConstant;
                            if (equalsToSerializable.equalsToString(str)) {
                                return (T) enumConstant;
                            }
                        }
                    }
                }
                if (ClassUtils.isAssignable(clazz, IntSerializable.class) && object instanceof Integer) {
                    Integer integer = (Integer) object;
                    for (Enum enumConstant : enumClass.getEnumConstants()) {
                        IntSerializable serializable = (IntSerializable) enumConstant;
                        if (integer.equals(serializable.serializeInt())) {
                            return (T) enumConstant;
                        }
                    }
                }
                throw new JSONException(String.format("Can't cast %s to %s", object, enumClass.getName()));
            }
        }
        throw new JSONException(String.format("Can't cast %s to %s", object, type.getTypeName()));
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
