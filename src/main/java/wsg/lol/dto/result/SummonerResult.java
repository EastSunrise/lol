package wsg.lol.dto.result;

import wsg.lol.common.base.BaseResult;
import wsg.lol.dmo.league.PositionDmo;
import wsg.lol.dmo.summoner.SummonerDmo;

/**
 * @author King
 * @date 2019/3/9
 */
public class SummonerResult extends BaseResult {

    private SummonerDmo summonerDmo;

    private PositionDmo positionDmo;

    public SummonerDmo getSummonerDmo() {
        return summonerDmo;
    }

    public void setSummonerDmo(SummonerDmo summonerDmo) {
        this.summonerDmo = summonerDmo;
    }

    public PositionDmo getPositionDmo() {
        return positionDmo;
    }

    public void setPositionDmo(PositionDmo positionDmo) {
        this.positionDmo = positionDmo;
    }
}
