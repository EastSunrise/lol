package wsg.lol.common.pojo.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer;

import java.lang.reflect.Type;

/**
 * // TODO: (Kingen, 2019/11/18) *
 *
 * @author Kingen
 */
public class FlattenDeserializer extends ContextObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName, String format, int features) {
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
