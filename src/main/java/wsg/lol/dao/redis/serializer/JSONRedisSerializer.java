package wsg.lol.dao.redis.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import wsg.lol.common.constants.Constants;

/**
 * wsg
 *
 * @author wangsigen
 */
public class JSONRedisSerializer<T> implements RedisSerializer<T> {

    private Class<T> clazz;

    public JSONRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(Constants.DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String string = new String(bytes, Constants.DEFAULT_CHARSET);
        return JSON.parseObject(string, clazz);
    }
}
