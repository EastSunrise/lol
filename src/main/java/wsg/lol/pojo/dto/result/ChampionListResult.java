package wsg.lol.pojo.dto.result;

import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.dmo.champion.ChampionDmo;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-20 11:02
 */
public class ChampionListResult extends BaseResult {

    private List<ChampionDmo> championDmoList;

    public List<ChampionDmo> getChampionDmoList() {
        return championDmoList;
    }

    public void setChampionDmoList(List<ChampionDmo> championDmoList) {
        this.championDmoList = championDmoList;
    }
}
