package wsg.lol.common.pojo.deserializer;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Type;

/**
 * Custom parse config.
 *
 * @author Kingen
 */
public class CustomParseConfig extends ParserConfig {

    /**
     * Use custom deserializer if defined in the type.
     */
    @Override
    public ObjectDeserializer getDeserializer(Class<?> clazz, Type type) {
        if (ClassUtils.isAssignable(clazz, CustomDeserializer.class)) {
            // TODO: (Kingen, 2019/11/20)
            return null;
        }
        return super.getDeserializer(clazz, type);
    }
}
