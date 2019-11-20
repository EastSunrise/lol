package wsg.lol.common.enums.champion;

import wsg.lol.common.pojo.parser.JsonSerializable;

/**
 * Enums for tags of the champion.
 *
 * @author Kingen
 */
public enum ChampionTagEnum implements JsonSerializable {
    Fighter("战士"),
    Tank("坦克"),
    Assassin("刺客"),
    Support("辅助"),
    Mage("法师"),
    Marksman("射手"),
    ;

    private String title;

    ChampionTagEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String serialize() {
        return name();
    }
}
