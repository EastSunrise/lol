package wsg.lol.dao.redis.intf;

import java.util.Map;

/**
 * wsg
 *
 * @author EastSunrise
 */
public interface RedisMapDao {


    /**
     * HashGet
     *
     * @param key
     *         not null
     * @param item
     *         not null
     */
    Object getHash(String key, String item);

    /**
     * 获取hashKey对应的所有键值
     */
    Map<Object, Object> getHashMap(String key);

    /**
     * HashSet
     *
     * @return true 成功 false 失败
     */
    boolean setHashMap(String key, Map<String, Object> map);

    /**
     * HashSet 并设置时间
     *
     * @return true成功 false失败
     */
    boolean setHashMap(String key, Map<String, Object> map, long time);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @return true 成功 false失败
     */
    boolean setHash(String key, String item, Object value);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param time
     *         时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     *
     * @return true 成功 false失败
     */
    boolean setHash(String key, String item, Object value, long time);

    /**
     * 删除hash表中的值
     *
     * @param key
     *         not null
     * @param item
     *         not null
     */
    void deleteHash(String key, Object... item);

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key
     *         not null
     * @param item
     *         not null
     *
     * @return true 存在 false不存在
     */
    boolean hasHashKey(String key, String item);

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     */
    double hashIncrement(String key, String item, double by);

    /**
     * hash递减
     */
    double hashDecrement(String key, String item, double by);
}
