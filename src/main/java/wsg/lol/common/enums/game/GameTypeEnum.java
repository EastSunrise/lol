package wsg.lol.common.enums.game;

/**
 * 游戏类型枚举
 *
 * @author EastSunrise
 * @date 2019/11/6
 * @see <a href="http://static.developer.riotgames.com/docs/lol/gameTypes.json>gameTypes.jspn</a>
 * @since 1.0
 */
public enum GameTypeEnum {
    CUSTOM_GAME("CUSTOM_GAME", "Custom games");

    private String gameType;
    private String description;

    GameTypeEnum(String gameType, String description) {
        this.gameType = gameType;
        this.description = description;
    }

    public String getGameType() {
        return gameType;
    }

    public String getDescription() {
        return description;
    }
}
