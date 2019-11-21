package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.pojo.dto.champion.SpellDto;
import wsg.lol.common.pojo.dto.general.ImageDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.service.intf.ChampionService;
import wsg.lol.service.intf.SummonerService;
import wsg.lol.service.intf.SystemService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class SummonerServiceImpl implements SummonerService {

    private static final Logger logger = LoggerFactory.getLogger(SummonerService.class);

    private DragonDao dragonDao;

    private ChampionService championService;

    private SystemService systemService;

    @Override
    @Transactional
    public Result updateSummonerSpells(String version) {
        logger.info("Updating the summoner spells.");
        List<SpellDto> spellDtoList = dragonDao.readSummonerSpells(version);
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (SpellDto spellDto : spellDtoList) {
            spellDto.setNum(SpellNumEnum.S);
            ImageDto image = spellDto.getImage();
            image.setRelatedId(spellDto.getKey());
            image.setGroup(ImageGroupEnum.SummonerSpell);
            imageDtoList.add(image);
        }
        ResultUtils.assertSuccess(championService.updateSpells(spellDtoList, SpellNumEnum.S));
        logger.info("Updating the images of summoner spells.");
        ResultUtils.assertSuccess(systemService.updateImages(imageDtoList, ImageGroupEnum.SummonerSpell));

        logger.info("Succeed in updating the data of summoner spells.");
        return ResultUtils.success();
    }

    @Autowired
    public void setDragonDao(DragonDao dragonDao) {
        this.dragonDao = dragonDao;
    }

    @Autowired
    public void setChampionService(ChampionService championService) {
        this.championService = championService;
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }
}
