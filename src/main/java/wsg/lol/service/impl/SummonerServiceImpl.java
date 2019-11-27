package wsg.lol.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import wsg.lol.common.ChampionMasteryDo;
import wsg.lol.common.SummonerDo;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.ListResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.pojo.dto.summoner.ChampionMasteryDto;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.ChampionMasteryV4;
import wsg.lol.dao.api.impl.SummonerV4;
import wsg.lol.dao.mybatis.mapper.summoner.ChampionMasteryMapper;
import wsg.lol.dao.mybatis.mapper.summoner.SummonerMapper;
import wsg.lol.service.common.MapperExecutor;
import wsg.lol.service.intf.SummonerService;

import java.util.Date;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class SummonerServiceImpl implements SummonerService {

    private static final Logger logger = LoggerFactory.getLogger(SummonerService.class);

    private SummonerMapper summonerMapper;

    private SummonerV4 summonerV4;

    private ChampionMasteryV4 championMasteryV4;

    private ChampionMasteryMapper championMasteryMapper;

    @Override
    @Performance
    public ListResult<SummonerDto> getSummonersForUpdate(RowBounds rowBounds) {
        Example example = new Example(SummonerDto.class);
        example.orderBy("lastUpdate");
        List<SummonerDto> summoners = summonerMapper.selectByExampleAndRowBounds(example, rowBounds);
        ListResult<SummonerDto> result = new ListResult<>();
        result.setList(summoners);
        return result;
    }

    @Override
    @Performance
    public ListResult<SummonerDto> getSummonersForMatch(RowBounds rowBounds) {
        Example example = new Example(SummonerDto.class);
        example.orderBy("lastMatch");
        List<SummonerDto> summoners = summonerMapper.selectByExampleAndRowBounds(example, rowBounds);
        ListResult<SummonerDto> result = new ListResult<>();
        result.setList(summoners);
        return result;
    }

    @Override
    @Performance
    public Result updateSummonerInfo(String summonerId) {
        logger.info("Updating the summoner {}.", summonerId);
        SummonerDto summoner = summonerV4.getSummonerById(summonerId);
        SummonerDo summonerDo = ObjectTransfer.transfer(summoner, SummonerDo.class);
        int score = championMasteryV4.getScoreBySummonerId(summonerId);
        summonerDo.setScore(score);
        summonerDo.setLastUpdate(new Date());
        int count = summonerMapper.updateByPrimaryKey(summonerDo);
        if (count != 1) {
            logger.error("Failed to update the summoner {}.", summonerId);
            throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to update the summoner " + summonerId);
        }
        return ResultUtils.success();
    }

    @Override
    @Performance
    public Result updateSummonerLastMatch(String accountId, Date lastMatch) {
        logger.info("Updating the last match of the account {}.", accountId);
        int count = summonerMapper.updateLastMatch(accountId, lastMatch);
        if (count != 1) {
            logger.error("Failed to update the last match of the account {}.", accountId);
            return ResultUtils.fail(ErrorCodeConst.DATABASE_ERROR, "Failed to update the last match of the account " + accountId);
        }
        return ResultUtils.success();
    }

    @Override
    @Transactional
    @Performance
    public Result updateChampionMasteries(String summonerId) {
        logger.info("Updating champion masteries of {}.", summonerId);
        List<ChampionMasteryDto> championMasteries = championMasteryV4.getChampionMasteryBySummonerId(summonerId);
        ResultUtils.assertSuccess(MapperExecutor.updateList(championMasteryMapper, ObjectTransfer.transferList(championMasteries, ChampionMasteryDo.class)));
        return ResultUtils.success();
    }

    @Autowired
    public void setChampionMasteryMapper(ChampionMasteryMapper championMasteryMapper) {
        this.championMasteryMapper = championMasteryMapper;
    }

    @Autowired
    public void setSummonerV4(SummonerV4 summonerV4) {
        this.summonerV4 = summonerV4;
    }

    @Autowired
    public void setChampionMasteryV4(ChampionMasteryV4 championMasteryV4) {
        this.championMasteryV4 = championMasteryV4;
    }

    @Autowired
    public void setSummonerMapper(SummonerMapper summonerMapper) {
        this.summonerMapper = summonerMapper;
    }
}
