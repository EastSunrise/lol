package wsg.lol.common.pojo.serialize;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;

import java.lang.reflect.Type;
import java.time.Duration;

/**
 * Deserializer for {@link java.time.Duration}
 *
 * @author Kingen
 */
public class DurationDeserializer implements ObjectDeserializer {

    private static final Logger logger = LoggerFactory.getLogger(DurationDeserializer.class);

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object object = parser.parse(fieldName);
        if (object == null) {
            return null;
        }

        Class<?> clazz = object.getClass();
        if (object instanceof Integer) {
            return (T) durationOf(((Integer) object).longValue());
        }
        if (object instanceof Long) {
            return (T) durationOf((Long) object);
        }

        if (ClassUtils.isAssignable(clazz, CharSequence.class)) {
            return (T) Duration.parse((CharSequence) object);
        }
        logger.error("Can't deserialize {} to {}.", object, Duration.class);
        throw new AppException(ErrorCodeConst.ILLEGAL_ARGS);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    protected Duration durationOf(long value) {
        return Duration.ofMillis(value);
    }
}
