package wsg.lol.pojo.dto.api.league;

import wsg.lol.pojo.base.BaseDto;

/**
 * wsg
 *
 * @author wangsigen
 */
public class MiniSeriesDto extends BaseDto {

    private Integer target;
    private Integer wins;
    private Integer losses;

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }
}
