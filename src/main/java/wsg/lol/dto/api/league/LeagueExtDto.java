package wsg.lol.dto.api.league;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJSONTransfer;
import wsg.lol.common.utils.BeanUtil;
import wsg.lol.dmo.league.ItemDmo;
import wsg.lol.dmo.league.LeagueDmo;

import java.util.LinkedList;
import java.util.List;

/**
 * @author King
 * @date 2019/2/12
 */
public class LeagueExtDto extends BaseDto implements IJSONTransfer {

    private LeagueDmo leagueDmo;

    private List<ItemDmo> itemDmoList;

    @Override
    public void parseFromJSONObject(JSONObject league) {
        LeagueDmo leagueDmo = JSON.toJavaObject(league, LeagueDmo.class);
        setLeagueDmo(leagueDmo);
        String leagueId = leagueDmo.getLeagueId();

        List<ItemDmo> itemDmoList = new LinkedList<>();
        for (Object object : league.getJSONArray("entries")) {
            JSONObject item = (JSONObject) object;
            ItemDmo itemDmo = JSON.toJavaObject(item, ItemDmo.class);
            BeanUtil.parseFromJSONObject(itemDmo, item);
            itemDmo.setLeagueId(leagueId);
            itemDmoList.add(itemDmo);
        }
        setItemDmoList(itemDmoList);
    }

    public LeagueDmo getLeagueDmo() {
        return leagueDmo;
    }

    public void setLeagueDmo(LeagueDmo leagueDmo) {
        this.leagueDmo = leagueDmo;
    }

    public List<ItemDmo> getItemDmoList() {
        return itemDmoList;
    }

    public void setItemDmoList(List<ItemDmo> itemDmoList) {
        this.itemDmoList = itemDmoList;
    }
}
