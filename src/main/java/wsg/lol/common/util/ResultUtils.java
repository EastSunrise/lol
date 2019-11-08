package wsg.lol.common.util;

import wsg.lol.common.constant.SystemConst;
import wsg.lol.common.pojo.base.BaseResult;

/**
 * // TODO: (wangsigen, 2019/11/8)
 *
 * @author wangsigen
 * @date 2019/11/8
 * @since 2.4.9.3
 */
public class ResultUtils {

    public static BaseResult success() {
        BaseResult result = new BaseResult();
        result.setSuccess(true);
        return result;
    }

    public static BaseResult fail(String errorCode) {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        return result;
    }

    public static BaseResult fail(String errorCode, String errorMsg) {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setMessage(errorMsg);
        return result;
    }

    public static BaseResult fail(String errorCode, String message, String[] args) {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setMessage(message);
        result.setArgs(args);
        return result;
    }

    public static BaseResult fail(Exception e) {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        result.setErrorCode(SystemConst.SYSTEM_ERROR);
        result.setMessage(e.toString());
        return result;
    }
}
