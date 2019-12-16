package wsg.lol.common.pojo.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import wsg.lol.config.CustomParser;

import java.nio.charset.StandardCharsets;

/**
 * Serializer of fastjson for redis.
 *
 * @author Kingen
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    private Class<T> clazz;

    public FastJsonRedisSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        String text = JSON.toJSONString(t, SerializerFeature.WriteClassName);
        return text.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String text = new String(bytes, StandardCharsets.UTF_8);
        return CustomParser.parseObjectDefault(text, clazz, Feature.SupportAutoType);
    }
}
