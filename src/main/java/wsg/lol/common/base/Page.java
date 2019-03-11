package wsg.lol.common.base;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-04 12:41
 */
public class Page {

    private int pageIndex;

    private int pageSize;

    public Page() {
        this.pageIndex = 0;
        this.pageSize = 100;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
