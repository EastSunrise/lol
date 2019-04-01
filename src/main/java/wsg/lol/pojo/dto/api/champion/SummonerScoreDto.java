package wsg.lol.pojo.dto.api.champion;

import wsg.lol.pojo.base.BaseDto;
import wsg.lol.pojo.base.IJson;

/**
 * wsg
 *
 * @author wangsigen
 */
public class SummonerScoreDto extends BaseDto implements IJson {

    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
