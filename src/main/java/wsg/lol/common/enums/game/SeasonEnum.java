package wsg.lol.common.enums.game;

/**
 * Enums for seasons.
 *
 * @author Kingen
 * @see <a href="http://static.developer.riotgames.com/docs/lol/seasons.json">seasons.json</a>
 */
public enum SeasonEnum {
    PRESEASON3(0, "PRESEASON 3"),
    SEASON3(1, "SEASON 3"),
    PRESEASON2014(2, "PRESEASON 2014"),
    SEASON2014(3, "SEASON 2014"),
    PRESEASON2015(4, "PRESEASON 2015"),
    SEASON2015(5, "SEASON 2015"),
    PRESEASON2016(6, "PRESEASON 2016"),
    SEASON2016(7, "SEASON 2016"),
    PRESEASON2017(8, "PRESEASON 2017"),
    SEASON2017(9, "SEASON 2017"),
    PRESEASON2018(10, "PRESEASON 2018"),
    SEASON2018(11, "SEASON 2018"),
    PRESEASON2019(12, "PRESEASON 2019"),
    SEASON2019(13, "SEASON 2019");

    private int id;
    private String season;

    SeasonEnum(int id, String season) {
        this.id = id;
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public String getSeason() {
        return season;
    }
}
