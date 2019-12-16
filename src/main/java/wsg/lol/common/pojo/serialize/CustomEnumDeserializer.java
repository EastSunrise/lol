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

    public final static CustomEnumDeserializer instance = new CustomEnumDeserializer();

    private CustomEnumDeserializer() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object object = parser.parse(fieldName);
        if (object == null) {
            return null;
        }
        if (type instanceof Class<?>) {
            Class<?> clazz = (Class<?>) type;
            if (clazz.isEnum()) {
                Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) type;
                if (ClassUtils.isAssignable(clazz, JSONSerializable.class)) {
                    for (Enum<?> enumConstant : enumClass.getEnumConstants()) {
                        JSONSerializable<?> serializable = (JSONSerializable<?>) enumConstant;
                        if (object.equals(serializable.serialize())) {
                            return (T) enumConstant;
                        }
                    }
                }
                if (ClassUtils.isAssignable(clazz, EqualsToSerializable.class)) {
                    for (Enum<?> enumConstant : enumClass.getEnumConstants()) {
                        EqualsToSerializable<Object> equalsToSerializable = (EqualsToSerializable<Object>) enumConstant;
                        if (equalsToSerializable.equalsToObject(object)) {
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
