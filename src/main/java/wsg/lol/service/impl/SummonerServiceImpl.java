package wsg.lol.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.annotation.AssignApi;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.ListResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ConfigConst;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.pojo.domain.summoner.ChampionMasteryDo;
import wsg.lol.common.pojo.domain.summoner.LeagueEntryDo;
import wsg.lol.common.pojo.domain.summoner.SummonerDo;
import wsg.lol.common.pojo.dto.summoner.ChampionMasteryDto;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.config.ApiIdentifier;
import wsg.lol.dao.api.impl.ChampionMasteryV4;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.api.impl.SummonerV4;
import wsg.lol.dao.mybatis.mapper.region.summoner.ChampionMasteryMapper;
import wsg.lol.dao.mybatis.mapper.region.summoner.LeagueEntryMapper;
import wsg.lol.dao.mybatis.mapper.region.summoner.SummonerMapper;
import wsg.lol.service.common.MapperExecutor;
import wsg.lol.service.common.PriorityUtils;
import wsg.lol.service.intf.SummonerService;

import java.util.Date;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class SummonerServiceImpl implements SummonerService {

    private static final Logger logger = LoggerFactory.getLogger(SummonerService.class);

    private SummonerV4 summonerV4;

    private LeagueV4 leagueV4;

    private ChampionMasteryV4 championMasteryV4;

    private SummonerMapper summonerMapper;

    private ChampionMasteryMapper championMasteryMapper;

    private LeagueEntryMapper leagueEntryMapper;

    @Override
    @Transactional
    @AssignApi(encryptUsername = "#encryptUsername")
    public Result addSummoner(String summonerName, String encryptUsername) {
        logger.info("Adding the summoner {}...", summonerName);

        GenericResult<SummonerDto> summonersByName = getSummonersByName(summonerName);
        if (summonersByName.isSuccess() && summonersByName.getObject() != null) {
            logger.info("Summoner {} exists already.", summonerName);
            return ResultUtils.success();
        }

        SummonerDto summonerDto = summonerV4.getSummoner(summonerName, SummonerV4.CondKeyEnum.NAME);
        String summonerId = summonerDto.getId();

        SummonerDo summonerDo = ObjectTransfer.transferDto(summonerDto, SummonerDo.class);
        int score = championMasteryV4.getScoreBySummonerId(summonerId);
        summonerDo.setScore(score);
        summonerDo.setLastUpdate(new Date());
        summonerDo.setLastMatch(ConfigConst.MATCH_BEGIN_DATE);
        summonerDo.setEncryptUsername(ApiIdentifier.getApi());
        int count = summonerMapper.insert(summonerDo);
        if (count != 1) {
            logger.error("Failed to inert the summoner {}.", summonerName);
            throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to inert the summoner " + summonerName);
        }

        logger.info("Adding champion masteries of {}...", summonerName);
        List<ChampionMasteryDto> championMasteries = championMasteryV4.getChampionMasteryBySummonerId(summonerId);
        Result result = MapperExecutor.insertList(championMasteryMapper, ObjectTransfer.transferDtoList(championMasteries, ChampionMasteryDo.class));
        ResultUtils.assertSuccess(result);

        logger.info("Adding league entries of {}...", summonerName);
        List<LeagueEntryDto> entries = leagueV4.getLeagueEntriesBySummonerId(summonerId);
        result = MapperExecutor.insertList(leagueEntryMapper, ObjectTransfer.transferDtoList(entries, LeagueEntryDo.class));
        ResultUtils.assertSuccess(result);

        logger.info("Added the summoner {}.", summonerName);
        return ResultUtils.success();
    }

    @Override
    @Transactional
    @AssignApi(encryptUsername = "#encryptUsername")
    public Result updateSummoner(String summonerId, String encryptUsername) {
        logger.info("Updating the summoner {}...", summonerId);

        logger.info("Updating champion masteries of {}...", summonerId);
        List<ChampionMasteryDto> championMasteries = championMasteryV4.getChampionMasteryBySummonerId(summonerId);
        ResultUtils.assertSuccess(MapperExecutor.replaceList(championMasteryMapper, ObjectTransfer.transferDtoList(championMasteries, ChampionMasteryDo.class)));

        logger.info("Updating league entries of {}...", summonerId);
        List<LeagueEntryDto> entries = leagueV4.getLeagueEntriesBySummonerId(summonerId);
        ResultUtils.assertSuccess(MapperExecutor.replaceList(leagueEntryMapper, ObjectTransfer.transferDtoList(entries, LeagueEntryDo.class)));

        SummonerDto summoner = summonerV4.getSummonerById(summonerId);
        SummonerDo summonerDo = ObjectTransfer.transferDto(summoner, SummonerDo.class);
        int score = championMasteryV4.getScoreBySummonerId(summonerId);
        summonerDo.setScore(score);
        summonerDo.setLastUpdate(new Date());
        int count = summonerMapper.updateByPrimaryKeySelective(summonerDo);
        if (count != 1) {
            logger.error("Failed to update the summoner {}.", summonerId);
            throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to update the summoner " + summonerId);
        }

        logger.info("Updated the summoner {}", summonerId);
        return ResultUtils.success();
    }

    @Override
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
    public Result updateSummonerLastUpdate(String summonerId, Date lastUpdate) {
        logger.info("Updating the last time updating the summoner {}.", summonerId);
        int count = summonerMapper.updateLastUpdate(summonerId, lastUpdate);
        if (count != 1) {
            logger.error("Failed to update the last time updating the summoner {}.", summonerId);
            return ResultUtils.fail(ErrorCodeConst.DATABASE_ERROR, "Failed to update the last time updating the summoner " + summonerId);
        }
        return ResultUtils.success();
    }

    @Override
    public ListResult<SummonerDto> getSummonersForUpdate(RowBounds rowBounds) {
        List<SummonerDo> summoners = summonerMapper.selectByExampleAndRowBounds(PriorityUtils.updateSummoners(), rowBounds);
        List<SummonerDto> summonerDtoList = ObjectTransfer.transferDoList(summoners, SummonerDto.class);
        return ListResult.create(summonerDtoList);
    }

    @Override
    public ListResult<SummonerDto> getSummonersForMatch(RowBounds rowBounds) {
        List<SummonerDo> summoners = summonerMapper.selectByExampleAndRowBounds(PriorityUtils.updateMatches(), rowBounds);
        List<SummonerDto> summonerDtoList = ObjectTransfer.transferDoList(summoners, SummonerDto.class);
        return ListResult.create(summonerDtoList);
    }

    @Override
    public GenericResult<SummonerDto> getSummonersByName(String summonerName) {
        SummonerDo cond = new SummonerDo();
        cond.setName(summonerName);
        SummonerDo summonerDo = summonerMapper.selectOne(cond);
        SummonerDto summonerDto = ObjectTransfer.transferDo(summonerDo, SummonerDto.class);
        return GenericResult.create(summonerDto);
    }

    @Autowired
    public void setLeagueEntryMapper(LeagueEntryMapper leagueEntryMapper) {
        this.leagueEntryMapper = leagueEntryMapper;
    }

    @Autowired
    public void setLeagueV4(LeagueV4 leagueV4) {
        this.leagueV4 = leagueV4;
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
