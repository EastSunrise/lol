package wsg.lol.common.enums.match;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enums for the type of the game.
 *
 * @author Kingen
 * @see <a href="http://static.developer.riotgames.com/docs/lol/gameTypes.json>gameTypes.jspn</a>
 */
public enum GameTypeEnum implements JSONSerializable<String> {
    CUSTOM_GAME("Custom games"),
    MATCHED_GAME("");

    private String description;

    GameTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String serialize() {
        return name();
    }
}
