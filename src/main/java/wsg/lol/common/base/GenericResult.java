package wsg.lol.common.base;

/**
 * Generic result.
 *
 * @author Kingen
 */
public class GenericResult<T> extends Result {

    private T t;

    protected GenericResult() {
    }

    public static <T> GenericResult<T> create(T t) {
        GenericResult<T> result = new GenericResult<>();
        result.setObject(t);
        return result;
    }

    public T getObject() {
        return t;
    }

    public void setObject(T t) {
        this.t = t;
    }
}
