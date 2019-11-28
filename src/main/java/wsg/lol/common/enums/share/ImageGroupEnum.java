package wsg.lol.common.enums.share;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enums for the group of the image.
 *
 * @author Kingen
 */
public enum ImageGroupEnum implements StringSerializable {
    Champion("champion"),
    Item("item"),
    Map("map"),
    Spell("spell"),
    Passive("passive"),
    SummonerSpell("summoner spell"),
    Mission("mission"),
    ProfileIcon("profileicon"),
    ;

    private String description;

    ImageGroupEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String serialize() {
        return getDescription();
    }
}
