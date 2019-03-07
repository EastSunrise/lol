package wsg.lol.dto.api.spectator;

/**
 * @author King
 * @date 2019/2/12
 */
public class GameCustomizationObject {

    /**
     * Category identifier for Game Customization
     */
    private String category;

    /**
     * Game Customization content
     */
    private String content;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
