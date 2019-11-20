package wsg.lol.common.pojo.parser;

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
            if (ClassUtils.isAssignable(clazz, Enum.class) && ClassUtils.isAssignable(clazz, JsonSerializable.class) && object instanceof String) {
                String str = (String) object;
                Class<? extends Enum> enumClass = (Class<? extends Enum>) type;
                for (Enum enumConstant : enumClass.getEnumConstants()) {
                    JsonSerializable serializable = (JsonSerializable) enumConstant;
                    if (str.equals(serializable.serialize())) {
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
