package wsg.lol.common.pojo.dto.query.state;

import wsg.lol.common.pojo.base.QueryDto;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class GetChampionDto extends QueryDto {

    private Integer championId;

    private String name;

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
