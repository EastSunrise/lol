package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.dto.match.MatchListDto;
import wsg.lol.common.pojo.dto.match.MatchReferenceDto;
import wsg.lol.common.pojo.query.QueryMatchListDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.MatchV4;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.MatchService;
import wsg.lol.service.intf.SummonerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class MatchServiceImpl implements MatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchService.class);

    private MatchV4 matchV4;

    private EventService eventService;

    private SummonerService summonerService;

    @Override
    @Transactional
    @Performance
    public Result updateMatches(String accountId, Date beginTime) {
        logger.info("Adding events of matches of the account {}.", accountId);
        List<Long> gameIds = new ArrayList<>();
        QueryMatchListDto queryMatchListDto = new QueryMatchListDto();
        queryMatchListDto.setBeginTime(beginTime.getTime());
        long endIndex = 0L, total;
        Date lastMatch;
        do {
            endIndex += QueryMatchListDto.MAX_INDEX_RANGE;
            queryMatchListDto.setEndIndex(endIndex);
            queryMatchListDto.setBeginIndex(endIndex - QueryMatchListDto.MAX_INDEX_RANGE);
            lastMatch = new Date();
            MatchListDto matchListDto = matchV4.getMatchListByAccount(accountId, queryMatchListDto);
            // todo event with platform
            for (MatchReferenceDto match : matchListDto.getMatches()) {
                gameIds.add(match.getGameId());
            }
            total = matchListDto.getTotalGames();
        } while (endIndex < total);

        Result result = eventService.insertEvents(EventTypeEnum.Match, gameIds);
        ResultUtils.assertSuccess(result);

        result = summonerService.updateSummonerLastMatch(accountId, lastMatch);
        ResultUtils.assertSuccess(result);

        logger.info("Succeed in adding events of matches of the account {}.", accountId);
        return ResultUtils.success();
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }

    @Autowired
    public void setMatchV4(MatchV4 matchV4) {
        this.matchV4 = matchV4;
    }
}
