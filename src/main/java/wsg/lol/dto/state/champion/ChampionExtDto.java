package wsg.lol.dto.state.champion;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJSONTransfer;
import wsg.lol.common.enums.impl.others.SpellTypeEnum;
import wsg.lol.common.utils.BeanUtil;
import wsg.lol.dmo.champion.ChampionDmo;
import wsg.lol.dmo.champion.SkinDmo;
import wsg.lol.dmo.champion.SpellDmo;
import wsg.lol.dmo.champion.TipDmo;

import java.util.LinkedList;
import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-05 13:46
 */
public class ChampionExtDto extends BaseDto implements IJSONTransfer {

    private ChampionDmo championDmo;

    private List<SkinDmo> skinDmoList;

    private List<TipDmo> tipDmoList;

    private SpellDmo passive;

    private List<SpellDmo> spellDmoList;

    @Override
    public void parseFromJSONObject(JSONObject champion) {
        ChampionDmo championDmo = new ChampionDmo();
        BeanUtil.parseFromJSONObject(championDmo, champion);
        int championId = champion.getInteger("key");
        championDmo.setId(championId);
        setChampionDmo(championDmo);

        List<SkinDmo> skinDmoList = JSON.parseArray(champion.getJSONArray("skins").toJSONString(), SkinDmo.class);
        for (SkinDmo skinDmo : skinDmoList) {
            skinDmo.setChampionId(championId);
        }
        setSkinDmoList(skinDmoList);

        List<TipDmo> tipDmoList = new LinkedList<>();
        for (Object allyTip : champion.getJSONArray("allytips")) {
            TipDmo tipDmo = new TipDmo();
            tipDmo.setChampionId(championId);
            tipDmo.setTip((String) allyTip);
            tipDmo.setType(false);
            tipDmoList.add(tipDmo);
        }
        for (Object enemyTip : champion.getJSONArray("enemytips")) {
            TipDmo tipDmo = new TipDmo();
            tipDmo.setChampionId(championId);
            tipDmo.setTip((String) enemyTip);
            tipDmo.setType(true);
            tipDmoList.add(tipDmo);
        }
        setTipDmoList(tipDmoList);

        List<SpellDmo> spellDmoList = new LinkedList<>();
        for (Object spell : champion.getJSONArray("spells")) {
            SpellDmo spellDmo = new SpellDmo();
            BeanUtil.parseFromJSONObject(spellDmo, (JSONObject) spell);
            spellDmo.setChampionId(championId);
            spellDmo.setSpellType(SpellTypeEnum.SPELL);
            spellDmoList.add(spellDmo);
        }
        setSpellDmoList(spellDmoList);

        SpellDmo spellDmo = new SpellDmo();
        BeanUtil.parseFromJSONObject(spellDmo, champion.getJSONObject("passive"));
        spellDmo.setId("p" + championId);
        spellDmo.setChampionId(championId);
        spellDmo.setSpellType(SpellTypeEnum.PASSIVE);
        setPassive(spellDmo);
    }

    public SpellDmo getPassive() {
        return passive;
    }

    public void setPassive(SpellDmo passive) {
        this.passive = passive;
    }

    public ChampionDmo getChampionDmo() {
        return championDmo;
    }

    public void setChampionDmo(ChampionDmo championDmo) {
        this.championDmo = championDmo;
    }

    public List<SkinDmo> getSkinDmoList() {
        return skinDmoList;
    }

    public void setSkinDmoList(List<SkinDmo> skinDmoList) {
        this.skinDmoList = skinDmoList;
    }

    public List<TipDmo> getTipDmoList() {
        return tipDmoList;
    }

    public void setTipDmoList(List<TipDmo> tipDmoList) {
        this.tipDmoList = tipDmoList;
    }

    public List<SpellDmo> getSpellDmoList() {
        return spellDmoList;
    }

    public void setSpellDmoList(List<SpellDmo> spellDmoList) {
        this.spellDmoList = spellDmoList;
    }
}
