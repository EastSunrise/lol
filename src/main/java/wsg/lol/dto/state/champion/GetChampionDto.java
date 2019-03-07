package wsg.lol.dto.state.champion;

import wsg.lol.common.base.QueryDto;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-28 17:21
 */
public class GetChampionDto extends QueryDto {

    private String championId;

    public String getChampionId() {
        return championId;
    }

    public void setChampionId(String championId) {
        this.championId = championId;
    }
}
