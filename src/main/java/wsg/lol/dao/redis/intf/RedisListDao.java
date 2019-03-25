package wsg.lol.dao.redis.intf;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface RedisListDao {

    /**
     * 获取list缓存的内容
     *
     * @param start
     *         开始
     * @param end
     *         结束 0 到 -1代表所有值
     */
    List<Object> getList(String key, long start, long end);

    /**
     * 获取list缓存的长度
     */
    Long getListSize(String key);

    /**
     * 通过索引 获取list中的值
     *
     * @param index
     *         索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */

    Object getListIndex(String key, long index);

    /**
     * 将list放入缓存
     */
    boolean setList(String key, Object value);

    /**
     * 将list放入缓存
     */
    boolean setList(String key, Object value, long time);

    /**
     * 将list放入缓存
     */
    boolean setList(String key, List<Object> value);

    /**
     * 将list放入缓存
     */
    boolean setList(String key, List<Object> value, long time);

    /**
     * 根据索引修改list中的某条数据
     */
    boolean updateListIndex(String key, long index, Object value);

    /**
     * 移除N个值为value
     *
     * @return 移除的个数
     */
    Long removeList(String key, long count, Object value);
}
