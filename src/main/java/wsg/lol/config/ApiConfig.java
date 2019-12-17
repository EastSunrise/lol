package wsg.lol.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration for api.
 *
 * @author Kingen
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiConfig {

    protected List<Token> tokens;
    private double rate;
    private Token single;

    @Setter
    @Getter
    public static class Token {
        private String key;
        private String username;
    }
}
