package wsg.lol.pojo.base;

/**
 * wsg
 *
 * @author wangsigen
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
