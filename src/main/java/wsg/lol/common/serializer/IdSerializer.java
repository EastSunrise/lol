package wsg.lol.common.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import wsg.lol.common.enums.intf.IdEnum;
import wsg.lol.common.utils.EnumUtil;

import java.lang.reflect.Type;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-06 14:13
 */
public class IdSerializer implements ObjectDeserializer, ObjectSerializer {
    @Override
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Integer id = defaultJSONParser.parseObject(Integer.class);
        return (T) EnumUtil.parseFromId(id, (Class) type);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type type, int i) {
        SerializeWriter out = jsonSerializer.getWriter();
        if (object == null) {
            out.writeNull();
            return;
        }
        IdEnum idEnum = (IdEnum) object;
        out.write(idEnum.getId());
    }
}
