package wsg.lol.service.scheduler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.common.base.Page;
import wsg.lol.common.base.ResultDto;
import wsg.lol.common.enums.impl.name.TierEnum;
import wsg.lol.common.enums.impl.others.RankQueueEnum;
import wsg.lol.dao.MatchMapper;
import wsg.lol.dao.SummonerMapper;
import wsg.lol.data.api.LeagueV4;
import wsg.lol.data.api.MatchV4;
import wsg.lol.data.api.SummonerV4;
import wsg.lol.dmo.league.ItemDmo;
import wsg.lol.dmo.match.MatchDmo;
import wsg.lol.dmo.match.ParticipantDmo;
import wsg.lol.dmo.summoner.SummonerDmo;
import wsg.lol.dto.api.league.LeagueExtDto;
import wsg.lol.dto.api.match.MatchDto;
import wsg.lol.dto.api.match.MatchListDto;
import wsg.lol.dto.api.match.QueryMatchListDto;
import wsg.lol.service.scheduler.intf.RealAction;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-28 14:47
 */
public class RealActionImpl implements RealAction {

    @Autowired
    private SummonerMapper summonerMapper;

    @Autowired
    private MatchMapper matchMapper;

    @Override
    public ResultDto buildBaseSummonerLibByLeague() {
        Set<String> summonerIdSet = new HashSet<>();

        // get apex summoners
        for (TierEnum tier : TierEnum.apexValues()) {
            for (RankQueueEnum queue : RankQueueEnum.values()) {
                LeagueExtDto leagueExtDto = LeagueV4.getApexLeagueByQueue(queue, tier);
                for (ItemDmo itemDmo : leagueExtDto.getItemDmoList()) {
                    summonerIdSet.add(itemDmo.getSummonerId());
                }
            }
        }

        // wsg get summoners of positional leagues
//        for (RankQueueEnum queue : RankQueueEnum.positionalValues()) {
//            for (TierEnum tier : TierEnum.positionalValues()) {
//                for (DivisionEnum division : DivisionEnum.values()) {
//                    for (PositionEnum position : PositionEnum.positionalValues()) {
//                        for (int i = 0; ; i++) {
//                            List<PositionDmo> positionDmoList = LeagueV4.getAllPositionLeagues(queue, tier, division,
//                                    position, i);
//                            if (positionDmoList == null || positionDmoList.isEmpty())
//                                break;
//                            for (PositionDmo positionDmo : positionDmoList) {
//                                summonerIdList.add(positionDmo.getSummonerId());
//                            }
//                        }
//                    }
//                }
//            }
//        }

        List<SummonerDmo> summonerDmoList = new LinkedList<>();
        for (String id : summonerIdSet) {
            summonerDmoList.add(SummonerV4.getSummonerById(id));
        }
        if (summonerDmoList.size() != summonerMapper.batchInsertSummoner(summonerDmoList)) {
            System.out.println("Fail to batch insert summoners.");
        }

        return ResultDto.success();
    }

    @Override
    public ResultDto extendSummonerLibByMatch() {
        // Get last unchecked summoners.
        List<SummonerDmo> summonerBaseList = summonerMapper.queryLastUncheckedSummoners(new Page());

        // Get the set of matchIds by accounts of summoners.
        Set<Long> gameIdSet = new HashSet<>();
        for (SummonerDmo summonerDmo : summonerBaseList) {
            QueryMatchListDto queryMatchListDto = new QueryMatchListDto();
            long beginTime = summonerDmo.getLastCheckedTime().getTime();
            queryMatchListDto.setBeginTime(beginTime);
            for (long beginIndex = 0; ; beginIndex += QueryMatchListDto.MAX_INDEX_RANGE) {
                queryMatchListDto.setBeginIndex(beginIndex);
                MatchListDto matchListDto = MatchV4.getMatchListByAccount(summonerDmo.getAccountId(),
                        queryMatchListDto);
                List<MatchDmo> matchDmoList = matchListDto.getMatches();
                if (matchDmoList.isEmpty())
                    break;
                for (MatchDmo match : matchDmoList) {
                    gameIdSet.add(match.getGameId());
                }
            }
        }

        // Get all summoners of these matches.
        Set<String> summonerIdSet = new HashSet<>();
        List<MatchDto> matchDtoList = new LinkedList<>();
        for (Long gameId : gameIdSet) {
            MatchDto matchDto = MatchV4.getMatchById(gameId);
            for (ParticipantDmo participantDmo : matchDto.getParticipantDmoList()) {
                summonerIdSet.add(participantDmo.getSummonerId());
            }
            matchDtoList.add(matchDto);
        }

        // Add info of summoners if not exist.
        List<SummonerDmo> summonerDmoList = new LinkedList<>();
        for (String id : summonerIdSet) {
            summonerDmoList.add(SummonerV4.getSummonerById(id));
        }
        if (summonerDmoList.size() != summonerMapper.batchInsertSummonerIfNotExist(summonerDmoList)) {
            System.out.println("Fail to batch insert summoners.");
        }

        // Add info of matches if not exist.
        matchMapper.batchInsertMatchIfNotExist(matchDtoList);

        return ResultDto.success();
    }

    @Override
    public ResultDto updateLeague() {
        return null;
    }
}
