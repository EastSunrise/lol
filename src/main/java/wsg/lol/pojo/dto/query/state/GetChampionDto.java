package wsg.lol.pojo.dto.query.state;

import wsg.lol.pojo.base.QueryStateDto;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-20 13:41
 */
public class GetChampionDto extends QueryStateDto {

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
