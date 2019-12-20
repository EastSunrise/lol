package wsg.lol.dao.api.client;

import lombok.Getter;
import lombok.Setter;

/**
 * Client for api.
 *
 * @author Kingen
 */
public interface ApiClient {

    /**
     * Get the token of api.
     */
    Token getToken();

    /**
     * When occurred that the token was forbidden.
     */
    void occurForbidden(Token token);

    /**
     * Get a valid username.
     */
    String getUsername();

    /**
     * Get the relative path of the lib.
     */
    String getPath();

    /**
     * Check if current region and the encrypt username match.
     */
    void checkEncrypt(String username);

    @Setter
    @Getter
    class Token {
        private String username;
        private String key;
    }
}
