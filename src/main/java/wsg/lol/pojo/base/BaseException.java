package wsg.lol.pojo.base;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-22 16:23
 */
public class BaseException extends RuntimeException {
    private String[] args;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, String[] args) {
        super(message);
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
