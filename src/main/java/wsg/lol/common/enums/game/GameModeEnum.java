package wsg.lol.common.enums.game;

/**
 * 游戏模式枚举类
 *
 * @author EastSunrise
 * @date 2019/11/6
 * @see <a href="http://static.developer.riotgames.com/docs/lol/gameModes.json">gameModes.json</a>
 * @since 1.0
 */
public enum GameModeEnum {
    CLASSIC("CLASSIC", "Classic Summoner's Rift and Twisted Treeline games");

    private String gameMode;
    private String description;

    GameModeEnum(String gameMode, String description) {
        this.gameMode = gameMode;
        this.description = description;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getDescription() {
        return description;
    }
}
