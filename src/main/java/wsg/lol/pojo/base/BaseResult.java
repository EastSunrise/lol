package wsg.lol.pojo.base;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-27 15:26
 */
public class BaseResult {

    private boolean success;

    private String errorMsg;

    public BaseResult() {
        setSuccess(true);
    }

    public BaseResult(boolean success) {
        this.success = success;
    }

    public BaseResult(boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public static BaseResult fail() {
        return new BaseResult(false);
    }

    public static BaseResult fail(String errorMsg) {
        return new BaseResult(false, errorMsg);
    }

    public static BaseResult success() {
        return new BaseResult();
    }

    public static BaseResult fail(Exception e) {
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
