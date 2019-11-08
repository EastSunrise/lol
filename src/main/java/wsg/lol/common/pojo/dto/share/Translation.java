package wsg.lol.common.pojo.dto.share;

import wsg.lol.common.pojo.base.BaseDto;

/**
 * @author EastSunrise
 */
public class Translation extends BaseDto {

    private String locale;
    private String content;
    private String updatedAt;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
