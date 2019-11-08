package wsg.lol.dao.redis.intf;

/**
 * wsg
 *
 * @author EastSunrise
 */
public interface RedisDao {

    /**
     * 指定缓存失效时间
     */
    boolean expire(String key, long time);

    /**
     * 根据key 获取过期时间
     *
     * @param key
     *         not null
     *
     * @return 时间(秒) 返回0代表为永久有效
     */
    Long getExpire(String key);

    /**
     * 判断key是否存在
     *
     * @return true 存在 false不存在
     */
    Boolean hasKey(String key);

    /**
     * 删除缓存
     */
    void delete(String... key);
}
