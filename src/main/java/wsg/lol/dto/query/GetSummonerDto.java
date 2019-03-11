package wsg.lol.dto.query;

import wsg.lol.common.base.QueryDto;

/**
 * @author King
 * @date 2019/3/9
 */
public class GetSummonerDto extends QueryDto {

    private String summonerName;

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }
}
