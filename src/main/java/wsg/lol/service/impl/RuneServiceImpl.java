package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.base.Result;
import wsg.lol.common.pojo.dto.rune.RuneDto;
import wsg.lol.common.pojo.dto.rune.RuneExtDto;
import wsg.lol.common.pojo.dto.rune.RuneTreeDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.dragon.intf.DragonDao;
import wsg.lol.dao.mybatis.mapper.rune.RuneMapper;
import wsg.lol.dao.mybatis.mapper.rune.RuneTreeMapper;
import wsg.lol.service.common.MapperExecutor;
import wsg.lol.service.intf.RuneService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class RuneServiceImpl implements RuneService {

    private static final Logger logger = LoggerFactory.getLogger(RuneService.class);

    private DragonDao dragonDao;

    private RuneMapper runeMapper;

    private RuneTreeMapper runeTreeMapper;

    @Override
    @Transactional
    public Result updateRunes(String version) {
        logger.info("Updating the data of runes.");
        List<RuneExtDto> runeExtDtoList = dragonDao.readRunes(version);

        logger.info("Updating the rune trees.");
        List<RuneTreeDto> runeTreeDtoList = new ArrayList<>(runeExtDtoList);
        ResultUtils.assertSuccess(MapperExecutor.updateStatic(runeTreeMapper, runeTreeDtoList));

        logger.info("Updating the runes.");
        List<RuneDto> runeDtoList = new ArrayList<>();
        for (RuneExtDto runeExtDto : runeExtDtoList) {
            runeDtoList.addAll(runeExtDto.getRunes());
        }
        ResultUtils.assertSuccess(MapperExecutor.updateStatic(runeMapper, runeDtoList));

        logger.info("Succeed in updating the data of runes.");
        return ResultUtils.success();
    }

    @Autowired
    public void setDragonDao(DragonDao dragonDao) {
        this.dragonDao = dragonDao;
    }

    @Autowired
    public void setRuneMapper(RuneMapper runeMapper) {
        this.runeMapper = runeMapper;
    }

    @Autowired
    public void setRuneTreeMapper(RuneTreeMapper runeTreeMapper) {
        this.runeTreeMapper = runeTreeMapper;
    }
}
