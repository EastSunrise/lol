package wsg.lol.common.util;

import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;

/**
 * // TODO: (Kingen, 2019/11/8)
 *
 * @author Kingen
 * @date 2019/11/8
 * @since 2.4.9.3
 */
public class AssertUtils {

    public static void isSuccess(Result result) {
        if (!result.isSuccess()) {
            throw new AppException(result);
        }
    }
}
