package wsg.lol.common.util;

import org.apache.ibatis.session.RowBounds;

/**
 * Util for page.
 *
 * @author Kingen
 */
public class PageUtils {

    public static RowBounds getRowBounds() {
        return new RowBounds(0, 100);
    }
}
