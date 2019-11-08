package wsg.lol.common.pojo.dto.query.state;

import wsg.lol.common.pojo.base.QueryDto;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class GetChampionListDto extends QueryDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
