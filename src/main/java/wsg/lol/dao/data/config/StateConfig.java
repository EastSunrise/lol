package wsg.lol.dao.data.config;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Component
@PropertySource(value = {"classpath:config/data-config.properties"})
public class StateConfig {

    @Value("${state.dir.cdn}")
    private String cdnDir;

    @Value("${state.language}")
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
