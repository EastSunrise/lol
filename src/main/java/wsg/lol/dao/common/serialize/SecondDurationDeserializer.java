package wsg.lol.dao.common.serialize;

import java.time.Duration;

/**
 * Deserializer for {@link Duration#ofSeconds(long)}
 *
 * @author Kingen
 */
public class SecondDurationDeserializer extends DurationDeserializer {

    @Override
    protected Duration durationOf(long value) {
        return Duration.ofSeconds(value);
    }
}
