package wsg.lol.dao.dragon.config;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Configurable arguments in config file.
 *
 * @author Kingen
 */
@Component
@PropertySource(value = {"classpath:config/data-config.properties"})
public class StaticConfig {

    @Value("${dragon.dir.cdn}")
    private String cdnDir;

    @Value("${dragon.language}")
    private String language;

    @Value("${state.url.cdn}")
    private String cdnUrl;

    public String getCdnDir() {
        return cdnDir;
    }

    public Locale getLanguage() {
        return LocaleUtils.toLocale(language);
    }

    public String getCdnUrl() {
        return cdnUrl;
    }
}
