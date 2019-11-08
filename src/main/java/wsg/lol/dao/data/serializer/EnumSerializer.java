package wsg.lol.dao.data.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import wsg.lol.common.util.EnumUtils;

import java.lang.reflect.Type;

/**
 * wsg
 *
 * @author EastSunrise
 */
public abstract class EnumSerializer implements ObjectDeserializer, ObjectSerializer {

    /**
     * @param object
     *         fieldName
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object object) {
        Object value = defaultJSONParser.parse();
        return (T) EnumUtils.parseFromValue(value, (Class) type);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
