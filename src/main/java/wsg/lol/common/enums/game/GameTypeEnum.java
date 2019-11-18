package wsg.lol.common.enums.game;

/**
 * Enums for the type of the game.
 *
 * @author Kingen
 * @see <a href="http://static.developer.riotgames.com/docs/lol/gameTypes.json>gameTypes.jspn</a>
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
