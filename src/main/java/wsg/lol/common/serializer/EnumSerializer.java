package wsg.lol.common.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import wsg.lol.common.utils.EnumUtil;

import java.lang.reflect.Type;

/**
 * wsg
 *
 * @author wangsigen
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
        return (T) EnumUtil.parseFromValue(value, (Class) type);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
