package wsg.lol.common.pojo.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Type;

/**
 * Custom deserializer for enum from an integer.
 *
 * @author Kingen
 */
public class IntegerEnumDeserializer implements ObjectDeserializer {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object object = parser.parse(fieldName);
        if (type instanceof Class<?>) {
            Class<?> clazz = (Class<?>) type;
            if (ClassUtils.isAssignable(clazz, Enum.class) && ClassUtils.isAssignable(clazz, IntegerSerializable.class) && object instanceof Integer) {
                Integer integer = (Integer) object;
                Class<? extends Enum> enumClass = (Class<? extends Enum>) type;
                for (Enum enumConstant : enumClass.getEnumConstants()) {
                    IntegerSerializable serializable = (IntegerSerializable) enumConstant;
                    if (integer.equals(serializable.serializeInteger())) {
                        return (T) enumConstant;
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
