package wsg.lol.common.util;

import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.result.base.Result;

/**
 * // TODO: (wangsigen, 2019/11/8)
 *
 * @author wangsigen
 * @date 2019/11/8
 * @since 2.4.9.3
 */
public class ResultUtils {

    public static Result success() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    public static Result fail(String errorCode) {
        Result result = new Result();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        return result;
    }

    public static Result fail(String errorCode, String errorMsg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setMessage(errorMsg);
        return result;
    }

    public static Result fail(String errorCode, String message, String[] args) {
        Result result = new Result();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setMessage(message);
        result.setArgs(args);
        return result;
    }

    public static Result fail(Exception e) {
        Result result = new Result();
        result.setSuccess(false);
        result.setErrorCode(ErrorCodeConst.SYSTEM_ERROR);
        result.setMessage(e.toString());
        return result;
    }
}
