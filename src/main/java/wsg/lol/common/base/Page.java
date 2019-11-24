package wsg.lol.common.base;

import lombok.Data;

/**
 * @author Kingen
 */
@Data
public class Page {

    private Integer index = 0;

    private Integer size = 1000;

    private Integer total;

    public Integer getIndex() {
        return index;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getTotal() {
        return total;
    }
}
