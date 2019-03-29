package wsg.lol.common.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * wsg
 *
 * @author wangsigen
 */
public class DateSerializer implements ObjectDeserializer, ObjectSerializer {

    @SuppressWarnings("unchecked")
    @Override
    public Date deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Long value = (Long) parser.parse();
        return new Date(value);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) {
        SerializeWriter out = serializer.getWriter();
        if (!(object instanceof Date)) {
            out.writeNull();
            return;
        }
        Date date = (Date) object;
        out.writeLong(date.getTime());
    }
}
