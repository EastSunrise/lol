package wsg.lol.common.pojo.dto.league;

import wsg.lol.common.base.BaseDto;

/**
 * todo
 *
 * @author EastSunrise
 */
public class MiniSeriesDto extends BaseDto {

    private String progress;
    private Integer target;
    private Integer wins;
    private Integer losses;

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

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
