package wsg.lol.common.enums.champion;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.apache.commons.lang3.ClassUtils;
import wsg.lol.common.pojo.deserializer.CustomDeserializer;

import java.lang.reflect.Type;

/**
 * // TODO: (Kingen, 2019/11/19) *
 *
 * @author Kingen
 */
public enum RecommendedTypeEnum implements CustomDeserializer {
    Riot("riot"),
    RiotSupport("riot-support"),
    RiotMid("riot-mid"),
    ;

    private String description;

    RecommendedTypeEnum(String description) {
        this.description = description;
    }

    public static void main(String[] args) {
        Type type = RecommendedTypeEnum.class;

        System.out.println(ClassUtils.isAssignable((Class<?>) type, CustomDeserializer.class));
    }

    public String getDescription() {
        return description;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ObjectDeserializer getDeserializer() {
        return new ObjectDeserializer() {
            @Override
            public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
                Object object = parser.parse(fieldName);
                if (object instanceof String) {
                    String description = (String) object;
                    for (RecommendedTypeEnum value : RecommendedTypeEnum.values()) {
                        if (value.getDescription().equals(description)) {
                            return (T) value;
                        }
                    }
                }
                throw new JSONException(String.format("Can't cast %s to %s", object, RecommendedTypeEnum.class.getName()));
            }

            @Override
            public int getFastMatchToken() {
                return 0;
            }
        };
    }
}
