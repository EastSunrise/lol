package wsg.lol.common.enums.match;

import wsg.lol.common.pojo.serialize.StringSerializable;

/**
 * Enums for the mode of the game.
 *
 * @author Kingen
 * @see <a href="http://static.developer.riotgames.com/docs/lol/gameModes.json">gameModes.json</a>
 */
public enum GameModeEnum implements StringSerializable {
    CLASSIC("Classic Summoner's Rift and Twisted Treeline games", "经典模式"),
    ODIN("Dominion/Crystal Scar games", "这货不是统治战场"),
    ARAM("ARAM games", "极地大乱斗"),
    TUTORIAL("Tutorial games", "新手教程"),
    URF("URF games"),
    DOOMBOTSTEEMO("Doom Bot games"),
    ONEFORALL("One for All games", "FRONTEND_oneforall_game_mode_name"),
    ASCENSION("Ascension games"),
    FIRSTBLOOD("Snowdown Showdown games", "FRONTEND_firstblood_game_mode_name"),
    KINGPORO("Legend of the Poro King games"),
    SIEGE("Nexus Siege games"),
    ASSASSINATE("Blood Hunt Assassin games"),
    ARSR("All Random Summoner's Rift games"),
    DARKSTAR("Dark Star: Singularity games"),
    STARGUARDIAN("Star Guardian Invasion games"),
    PROJECT("PROJECT: Hunters games"),
    GAMEMODEX("Nexus Blitz games"),
    ODYSSEY("Odyssey: Extraction games"),

    INTRO(""),
    TUTORIAL_MODULE_2(""),
    TUTORIAL_MODULE_3(""),
    any(""),
    ;

    private String description;
    private String title;

    GameModeEnum(String description) {
        this.description = description;
        this.title = this.name();
    }

    GameModeEnum(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String serialize() {
        return name();
    }
}
