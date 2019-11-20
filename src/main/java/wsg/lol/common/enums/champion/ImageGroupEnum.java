package wsg.lol.common.enums.champion;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import wsg.lol.common.pojo.deserializer.CustomDeserializer;

import java.lang.reflect.Type;

/**
 * Enums for the group of the image.
 *
 * @author Kingen
 */
public enum ImageGroupEnum implements CustomDeserializer {
    Champion,
    Item,
    Map,
    Spell,
    Passive,
    SummonerSpell,
    Mission, // TODO: (Kingen, 2019/11/19) mission-assets
    ProfileIcon,
    ;

    @Override
    public ObjectDeserializer getDeserializer() {
        return new ObjectDeserializer() {
            @Override
            public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
                return parser.parseObject(type, fieldName);
            }

            @Override
            public int getFastMatchToken() {
                return 0;
            }
        };
    }
}
