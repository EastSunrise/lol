package wsg.lol.common.base;

/**
 * Common result of service.
 *
 * @author Kingen
 */
public class Result {

    protected boolean success = true;

    protected String errorCode = "";

    protected String message = "";

    protected String[] args;

    public Result copy(Result result) {
        if (result != null) {
            this.success = result.success;
            this.errorCode = result.errorCode;
            this.message = result.message;
            this.args = result.args;
        }

        return this;
    }

    public void fail() {
        this.success = false;
    }

    public void fail(String errorCode) {
        this.success = false;
        this.errorCode = errorCode;
    }

    public void fail(String errorCode, String message) {
        this.success = false;
        this.errorCode = errorCode;
        this.message = message;
    }

    public void fail(String errorCode, String message, String[] args) {
        this.success = false;
        this.errorCode = errorCode;
        this.message = message;
        this.args = args;
    }

    public void fail(AppException ae) {
        this.fail(ae.getErrorCode(), ae.getMessage(), ae.getArgs());
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
