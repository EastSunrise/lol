package wsg.lol.common.pojo.base;

/**
 * 单个结果泛型类
 *
 * @author wangsigen
 * @date 2019/11/8
 * @since 2.4.9.3
 */
public class GenericResult<T> extends BaseResult {

    private T t;

    public T getObject() {
        return t;
    }

    public void setObject(T t) {
        this.t = t;
    }
}
