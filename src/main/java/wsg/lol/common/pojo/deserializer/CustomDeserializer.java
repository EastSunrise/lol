package wsg.lol.common.pojo.deserializer;

import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

/**
 * Custom deserializer for this type.
 *
 * @author Kingen
 */
public interface CustomDeserializer {

    ObjectDeserializer getDeserializer();
}
