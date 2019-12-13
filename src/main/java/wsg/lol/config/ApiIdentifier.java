package wsg.lol.config;

/**
 * Identifier to record current used api.
 *
 * @author Kingen
 */
public class ApiIdentifier {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * Get the username of the account that current api belongs to.
     */
    public static String getApi() {
        return threadLocal.get();
    }

    /**
     * Set current api by the username.
     */
    public static void setApi(String username) {
        threadLocal.set(username);
    }
}
