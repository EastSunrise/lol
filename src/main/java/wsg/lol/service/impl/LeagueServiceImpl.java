package wsg.lol.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.rank.DivisionEnum;
import wsg.lol.common.enums.rank.RankQueueEnum;
import wsg.lol.common.enums.rank.TierEnum;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.mybatis.mapper.system.EventMapper;
import wsg.lol.service.intf.LeagueService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kingen
 */
@Service
public class LeagueServiceImpl implements LeagueService {

    private static final Logger logger = LoggerFactory.getLogger(LeagueService.class);

    private LeagueV4 leagueV4;

    private EventMapper eventMapper;

    @Override
    @Transactional
    public Result initialByLeagues() {
        logger.info("Initializing the database by leagues.");
        List<String> ids = new ArrayList<>();
        for (RankQueueEnum queue : RankQueueEnum.values()) {
            for (TierEnum tier : TierEnum.values()) {
                for (DivisionEnum division : DivisionEnum.validDivisions(tier)) {
                    for (int page = 1; ; page++) {
                        List<LeagueEntryDto> leagueEntryDtoList = leagueV4.getLeagueEntriesExp(queue, tier, division, page);
                        if (leagueEntryDtoList == null || leagueEntryDtoList.isEmpty())
                            break;
                        for (LeagueEntryDto leagueEntryDto : leagueEntryDtoList) {
                            ids.add(leagueEntryDto.getSummonerId());
                        }
                    }
                }
            }
        }

        if (CollectionUtils.isEmpty(ids)) {
            logger.info("Not summoner ids to add as events.");
            return ResultUtils.success();
        }

        int count = eventMapper.insertEventsOfType(EventTypeEnum.SummonerId, EventStatusEnum.Unfinished, ids);
        if (count != ids.size()) {
            logger.error("Failed to inert events of {}.", EventTypeEnum.SummonerId);
            throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to inert events of summoners.");
        }

        return ResultUtils.success();
    }

    @Autowired
    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Autowired
    public void setLeagueV4(LeagueV4 leagueV4) {
        this.leagueV4 = leagueV4;
    }
}
