package wsg.lol.common.base;

/**
 * Generic result
 *
 * @author Kingen
 */
public class GenericResult<T> extends Result {

    private T t;

    public T getObject() {
        return t;
    }

    public void setObject(T t) {
        this.t = t;
    }
}
