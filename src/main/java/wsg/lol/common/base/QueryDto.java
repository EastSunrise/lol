package wsg.lol.common.base;

import java.util.Map;

/**
 * Base of query condition.
 *
 * @author Kingen
 */
public abstract class QueryDto {
    public abstract Map<String, Object> toMap();
}
