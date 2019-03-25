package wsg.lol.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.dao.mybatis.mapper.ChampionMapper;
import wsg.lol.dao.mybatis.mapper.SkinMapper;
import wsg.lol.dao.mybatis.mapper.SpellMapper;
import wsg.lol.pojo.dmo.champion.ChampionDmo;
import wsg.lol.pojo.dmo.champion.SpellDmo;
import wsg.lol.pojo.dto.query.GetChampionDto;
import wsg.lol.pojo.dto.query.GetChampionListDto;
import wsg.lol.pojo.dto.result.ChampionListResult;
import wsg.lol.pojo.dto.state.champion.ChampionExtDto;
import wsg.lol.pojo.enums.impl.others.SpellTypeEnum;
import wsg.lol.service.user.intf.ChampionService;

import java.util.Iterator;
import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-20 11:10
 */
@Service("championService")
public class ChampionServiceImpl implements ChampionService {

    @Autowired
    private ChampionMapper championMapper;

    @Autowired
    private SkinMapper skinMapper;

    @Autowired
    private SpellMapper spellMapper;

    @Override
    public ChampionListResult getChampionList(GetChampionListDto getChampionListDto) {
        ChampionListResult championListResult = new ChampionListResult();
        championListResult.setChampionDmoList(championMapper.selectChampions(getChampionListDto));
        return championListResult;
    }

    @Override
    public ChampionExtDto getChampionInfo(GetChampionDto getChampionDto) {
        ChampionExtDto championExtDto = new ChampionExtDto();
        ChampionDmo championDmo = championMapper.selectChampionByCond(getChampionDto);
        championExtDto.setChampionDmo(championDmo);
        championExtDto.setSkinDmoList(skinMapper.selectSkinsByChampion(championDmo.getId()));
        List<SpellDmo> spellDmoList = spellMapper.selectSpellsByChampion(championDmo.getId());
        Iterator<SpellDmo> iterator = spellDmoList.iterator();
        while (iterator.hasNext()) {
            SpellDmo spellDmo = iterator.next();
            if (SpellTypeEnum.PASSIVE.equals(spellDmo.getSpellType())) {
                championExtDto.setPassive(spellDmo);
                iterator.remove();
            }
        }
        championExtDto.setSpellDmoList(spellDmoList);
        return championExtDto;
    }
}
