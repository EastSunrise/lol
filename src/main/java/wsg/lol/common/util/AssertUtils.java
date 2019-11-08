package wsg.lol.common.util;

import wsg.lol.common.pojo.base.AppException;
import wsg.lol.common.pojo.base.BaseResult;

/**
 * // TODO: (wangsigen, 2019/11/8)
 *
 * @author wangsigen
 * @date 2019/11/8
 * @since 2.4.9.3
 */
public class AssertUtils {

    public static void isSuccess(BaseResult result) {
        if (!result.isSuccess()) {
            throw new AppException(result);
        }
    }
}
