package wsg.lol.common.enums.game;

/**
 * Enums for the mode of the game.
 *
 * @author Kingen
 * @see <a href="http://static.developer.riotgames.com/docs/lol/gameModes.json">gameModes.json</a>
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
