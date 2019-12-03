package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.annotation.Performance;
import wsg.lol.common.base.Result;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.enums.system.PlatformRoutingEnum;
import wsg.lol.common.pojo.dto.match.MatchListDto;
import wsg.lol.common.pojo.dto.match.MatchReferenceDto;
import wsg.lol.common.pojo.query.QueryMatchListDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.MatchV4;
import wsg.lol.dao.mybatis.config.DatabaseIdentifier;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.MatchService;
import wsg.lol.service.intf.SummonerService;

import java.util.*;

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
        logger.info("Adding events of matches of the account {}...", accountId);
        Map<PlatformRoutingEnum, Set<String>> map = new HashMap<>();
        QueryMatchListDto queryMatchListDto = new QueryMatchListDto();
        queryMatchListDto.setBeginTime(beginTime.getTime());
        long beginIndex = 0L, total;
        Date lastMatch;
        do {
            queryMatchListDto.setBeginIndex(beginIndex);
            lastMatch = new Date();
            MatchListDto matchListDto = matchV4.getMatchListByAccount(accountId, queryMatchListDto);
            for (MatchReferenceDto match : matchListDto.getMatches()) {
                PlatformRoutingEnum platform = match.getPlatformId();
                if (!map.containsKey(platform)) {
                    map.put(platform, new HashSet<>());
                }
                map.get(platform).add(match.getGameId().toString());
            }
            total = matchListDto.getTotalGames();
            beginIndex = matchListDto.getEndIndex();
        } while (beginIndex < total);

        for (Map.Entry<PlatformRoutingEnum, Set<String>> entry : map.entrySet()) {
            DatabaseIdentifier.setPlatform(entry.getKey());
            Result result = eventService.insertEvents(EventTypeEnum.Match, entry.getValue());
            ResultUtils.assertSuccess(result);
        }

        DatabaseIdentifier.setPlatform(null);
        Result result = summonerService.updateSummonerLastMatch(accountId, lastMatch);
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
