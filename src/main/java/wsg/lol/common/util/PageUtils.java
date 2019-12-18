package wsg.lol.common.util;

import org.apache.ibatis.session.RowBounds;

/**
 * Util for page.
 *
 * @author Kingen
 */
public class PageUtils {

    public static final RowBounds DEFAULT_PAGE = new RowBounds(0, 100);

    public static final RowBounds SINGLE = new RowBounds(0, 1);
}
