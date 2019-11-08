package wsg.lol.dao.redis.intf;

/**
 * wsg
 *
 * @author EastSunrise
 */
public interface RedisStringDao {

    /**
     * 普通缓存获取
     */
    Object get(String key);

    /**
     * 普通缓存放入
     *
     * @return true成功 false失败
     */
    boolean set(String key, Object value);

    /**
     * 普通缓存放入并设置时间
     *
     * @param time
     *         时间(秒) time要大于0 如果time小于等于0 将设置无限期
     *
     * @return true成功 false 失败
     */
    boolean set(String key, Object value, long time);

    /**
     * 递增
     */
    Long increment(String key, long delta);

    /**
     * 递减
     */
    Long decrement(String key, long delta);
}
