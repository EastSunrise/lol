package wsg.lol.pojo.result;

import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.dmo.league.PositionDmo;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;

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
