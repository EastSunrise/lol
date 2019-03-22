package wsg.lol.pojo.dto.api.others;

/**
 * @author King
 * @date 2019/2/11
 */
public class Translation {

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
