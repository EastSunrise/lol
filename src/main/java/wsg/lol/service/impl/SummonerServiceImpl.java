package wsg.lol.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import tk.mybatis.mapper.entity.Example;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.pojo.dto.summoner.ChampionMasteryDto;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.ChampionMasteryV4;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.api.impl.SummonerV4;
import wsg.lol.dao.mybatis.mapper.summoner.ChampionMasteryMapper;
import wsg.lol.dao.mybatis.mapper.summoner.LeagueEntryMapper;
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

    private TransactionTemplate transactionTemplate;

    private SummonerV4 summonerV4;

    private ChampionMasteryV4 championMasteryV4;

    private ChampionMasteryMapper championMasteryMapper;

    private LeagueV4 leagueV4;

    private LeagueEntryMapper leagueEntryMapper;

    @Override
    public Result updateSummoners(RowBounds rowBounds) {
        logger.info("Updating the older summoners.");
        Example example = new Example(SummonerDto.class);
        example.orderBy("lastUpdate");
        List<SummonerDto> summonerDtoList = summonerMapper.selectByExampleAndRowBounds(example, rowBounds);

        for (SummonerDto summonerDto : summonerDtoList) {
            String summonerId = summonerDto.getId();
            try {
                transactionTemplate.execute(transactionStatus -> {
                    logger.info("Update summoner {}.", summonerId);

                    // get base info.
                    SummonerDto summoner = summonerV4.getSummonerById(summonerId);
                    int score = championMasteryV4.getScoreBySummonerId(summonerId);
                    summoner.setScore(score);

                    logger.info("Updating champion masteries of {}.", summonerId);
                    List<ChampionMasteryDto> championMasteries = championMasteryV4.getChampionMasteryBySummonerId(summonerId);
                    ResultUtils.assertSuccess(MapperExecutor.updateList(championMasteryMapper, championMasteries));

                    logger.info("Updating league entries of {}.", summonerId);
                    List<LeagueEntryDto> entries = leagueV4.getLeagueEntriesBySummonerId(summonerId);
                    ResultUtils.assertSuccess(MapperExecutor.updateList(leagueEntryMapper, entries));

                    logger.info("Updating the summoner {}.", summonerId);
                    summoner.setLastUpdate(new Date());
                    int count = summonerMapper.updateByPrimaryKey(summoner);
                    if (count != 1) {
                        logger.error("Failed to update the summoner {}.", summonerId);
                        throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to update the summoner " + summonerId);
                    }

                    logger.info("Succeed in updating the summoner {}.", summonerId);
                    return ResultUtils.success();
                });
            } catch (AppException e) {
                e.printStackTrace();
            }
        }

        return ResultUtils.success();
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
    public void setChampionMasteryMapper(ChampionMasteryMapper championMasteryMapper) {
        this.championMasteryMapper = championMasteryMapper;
    }

    @Autowired
    public void setLeagueV4(LeagueV4 leagueV4) {
        this.leagueV4 = leagueV4;
    }

    @Autowired
    public void setLeagueEntryMapper(LeagueEntryMapper leagueEntryMapper) {
        this.leagueEntryMapper = leagueEntryMapper;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Autowired
    public void setSummonerMapper(SummonerMapper summonerMapper) {
        this.summonerMapper = summonerMapper;
    }
}
