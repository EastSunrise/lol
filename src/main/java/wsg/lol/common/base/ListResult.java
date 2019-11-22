package wsg.lol.common.base;

import java.util.List;

/**
 * Generic result for list of data.
 *
 * @author Kingen
 */
public class ListResult<T> extends Result {

    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
