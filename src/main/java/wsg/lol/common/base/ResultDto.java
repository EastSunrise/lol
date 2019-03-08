package wsg.lol.common.base;

import java.io.IOException;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-27 15:26
 */
public class ResultDto {

    private boolean success;

    private String errorMsg;

    public ResultDto() {
        setSuccess(true);
    }

    public ResultDto(boolean success) {
        this.success = success;
    }

    public ResultDto(boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public static ResultDto fail() {
        return new ResultDto(false);
    }

    public static ResultDto fail(String errorMsg) {
        return new ResultDto(false, errorMsg);
    }

    public static ResultDto success() {
        return new ResultDto();
    }

    public static ResultDto fail(IOException e) {
        return fail(e.getMessage());
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
