package wsg.lol.common.enums.share;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enums for seasons.
 *
 * @author Kingen
 * @see <a href="http://static.developer.riotgames.com/docs/lol/seasons.json">seasons.json</a>
 */
public enum SeasonEnum implements JSONSerializable<Integer> {
    PRESEASON3("PRESEASON 3"),
    SEASON3("SEASON 3"),
    PRESEASON2014("PRESEASON 2014"),
    SEASON2014("SEASON 2014"),
    PRESEASON2015("PRESEASON 2015"),
    SEASON2015("SEASON 2015"),
    PRESEASON2016("PRESEASON 2016"),
    SEASON2016("SEASON 2016"),
    PRESEASON2017("PRESEASON 2017"),
    SEASON2017("SEASON 2017"),
    PRESEASON2018("PRESEASON 2018"),
    SEASON2018("SEASON 2018"),
    PRESEASON2019("PRESEASON 2019"),
    SEASON2019("SEASON 2019");

    private String season;

    SeasonEnum(String season) {
        this.season = season;
    }

    public String getSeason() {
        return season;
    }

    @Override
    public Integer serialize() {
        return ordinal();
    }
}
