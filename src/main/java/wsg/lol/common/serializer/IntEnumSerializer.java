package wsg.lol.common.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import wsg.lol.pojo.enums.intf.BaseEnum;

import java.lang.reflect.Type;

/**
 * wsg
 *
 * @author wangsigen
 */
public class IntEnumSerializer extends EnumSerializer {

    @Override
    @SuppressWarnings("unchecked")
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type type, int i) {
        SerializeWriter out = jsonSerializer.getWriter();
        if (object == null) {
            out.writeNull();
            return;
        }
        BaseEnum<Integer> baseEnum = (BaseEnum<Integer>) object;
        out.writeInt(baseEnum.getValue());
    }
}
