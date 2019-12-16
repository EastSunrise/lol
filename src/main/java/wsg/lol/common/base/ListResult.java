package wsg.lol.common.base;

import java.util.List;

/**
 * Generic result for list of data.
 *
 * @author Kingen
 */
public class ListResult<T> extends Result {

    private List<T> list;

    protected ListResult() {
    }

    public static <T> ListResult<T> create(List<T> ts) {
        ListResult<T> result = new ListResult<>();
        result.setList(ts);
        return result;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
