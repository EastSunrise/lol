package wsg.lol.dao.redis.intf;

import java.util.Set;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface RedisSetDao {

    /**
     * 根据key获取Set中的所有值
     */
    Set<Object> getSet(String key);

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @return true 存在 false不存在
     */
    Boolean hasSetKey(String key, Object value);

    /**
     * 将数据放入set缓存
     *
     * @return 成功个数
     */
    Long setSet(String key, Object... values);

    /**
     * 将set数据放入缓存
     *
     * @return 成功个数
     */
    Long setSet(String key, long time, Object... values);

    /**
     * 获取set缓存的长度
     */
    Long getSetSize(String key);

    /**
     * 移除值为value的
     *
     * @return 移除的个数
     */
    Long removeSet(String key, Object... values);
}
