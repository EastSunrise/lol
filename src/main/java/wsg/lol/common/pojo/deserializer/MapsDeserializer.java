package wsg.lol.common.pojo.deserializer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import wsg.lol.common.enums.game.MapEnum;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Custom deserializer from key-value of available maps to array of {@link MapEnum}
 *
 * @author Kingen
 */
public class MapsDeserializer implements ObjectDeserializer {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONObject jsonObject = parser.parseObject();
        if (jsonObject == null) {
            return null;
        }
        List<MapEnum> maps = new ArrayList<>();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if ((boolean) entry.getValue()) {
                maps.add(MapEnum.map(Integer.parseInt(entry.getKey())));
            }
        }
        return (T) maps.toArray(new MapEnum[0]);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
