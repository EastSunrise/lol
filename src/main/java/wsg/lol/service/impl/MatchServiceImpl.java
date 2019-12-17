package wsg.lol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.annotation.AssignApi;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.pojo.domain.match.*;
import wsg.lol.common.pojo.dto.match.*;
import wsg.lol.common.pojo.query.QueryMatchListDto;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.impl.MatchV4;
import wsg.lol.dao.mybatis.config.DatabaseIdentifier;
import wsg.lol.dao.mybatis.mapper.region.match.*;
import wsg.lol.service.common.MapperExecutor;
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

    private MatchFrameMapper matchFrameMapper;

    private TeamStatsMapper teamStatsMapper;

    private MatchMapper matchMapper;

    private ParticipantMapper participantMapper;

    private ParticipantStatsMapper participantStatsMapper;

    private ParticipantFrameMapper participantFrameMapper;

    private EventService eventService;

    private SummonerService summonerService;

    @Override
    @Transactional
    @AssignApi(encryptUsername = "#encryptUsername")
    public Result updateMatches(String accountId, Date beginTime, String encryptUsername) {
        logger.info("Adding events of matches of the account {}...", accountId);
        Map<RegionEnum, Map<String, String>> map = new HashMap<>();
        QueryMatchListDto queryMatchListDto = new QueryMatchListDto();
        queryMatchListDto.setBeginTime(beginTime.getTime());
        long beginIndex = 0L, total;
        Date lastMatch;
        do {
            queryMatchListDto.setBeginIndex(beginIndex);
            lastMatch = new Date();
            MatchListDto matchListDto = matchV4.getMatchListByAccount(accountId, queryMatchListDto);
            for (MatchReferenceDto match : matchListDto.getMatches()) {
                RegionEnum region = match.getPlatformId();
                if (!map.containsKey(region)) {
                    map.put(region, new HashMap<>());
                }
                map.get(region).put(match.getGameId().toString(), region + "#" + accountId);
            }
            total = matchListDto.getTotalGames();
            beginIndex = matchListDto.getEndIndex();
        } while (beginIndex < total);

        // TODO: Switch the database
        for (Map.Entry<RegionEnum, Map<String, String>> entry : map.entrySet()) {
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

    @Override
    @Transactional
    @AssignApi
    public Result addMatch(long gameId) {
        logger.info("Adding the match {}.", gameId);
        MatchExtDto matchExtDto = matchV4.getMatchById(gameId);
        MatchTimelineDto timelineDto = matchV4.getTimelineByMatchId(gameId);

        logger.info("Adding frames of the match {}...", gameId);
        List<MatchFrameDo> frameDoList = new ArrayList<>();
        List<MatchFrameDto> frames = timelineDto.getFrames();
        for (MatchFrameDto frame : frames) {
            for (MatchEventDto frameEvent : frame.getEvents()) {
                MatchFrameDo frameDo = ObjectTransfer.transferDto(frameEvent, MatchFrameDo.class);
                frameDo.setGameId(gameId);
                frameDo.setTimeline(frame.getTimestamp());
                frameDo.setTiming(frameEvent.getTimestamp());
                frameDoList.add(frameDo);
            }
        }
        ResultUtils.assertSuccess(MapperExecutor.insertList(matchFrameMapper, frameDoList));

        logger.info("Adding stats of teams in the match {}...", gameId);
        List<TeamStatsDo> teams = new ArrayList<>();
        List<TeamStatsDto> teamStatsDtoList = matchExtDto.getTeams();
        for (TeamStatsDto teamStatsDto : teamStatsDtoList) {
            TeamStatsDo teamStatsDo = ObjectTransfer.transferDto(teamStatsDto, TeamStatsDo.class);
            teamStatsDo.setGameId(gameId);
            teams.add(teamStatsDo);
        }
        ResultUtils.assertSuccess(MapperExecutor.insertList(teamStatsMapper, teams));

        logger.info("Adding participants of the match {}...", gameId);
        List<ParticipantDo> participants = new ArrayList<>();
        Map<Integer, ParticipantStatsDo> num2Stats = new HashMap<>();
        Map<Integer, PlayerDto> num2Player = new HashMap<>();
        for (ParticipantIdentityDto identityDto : matchExtDto.getParticipantIdentities()) {
            num2Player.put(identityDto.getParticipantNum(), identityDto.getPlayer());
        }
        for (ParticipantDto participantDto : matchExtDto.getParticipants()) {
            ParticipantDo participantDo = ObjectTransfer.transferDto(participantDto, ParticipantDo.class);
            int num = participantDo.getParticipantNum();
            PlayerDto playerDto = num2Player.get(num);

            participantDo.setGameId(gameId);
            participantDo.generateId();

            participantDo.setPlatformId(playerDto.getPlatformId());
            participantDo.setMatchHistoryUri(playerDto.getMatchHistoryUri());
            participantDo.setProfileIcon(playerDto.getProfileIcon());

            // todo related summoner

            participantDo.setRole(participantDto.getTimeline().getRole());
            participantDo.setLane(participantDto.getTimeline().getLane());

            participants.add(participantDo);

            num2Stats.put(num, ObjectTransfer.transferDto(participantDto.getStats(), ParticipantStatsDo.class));
        }
        ResultUtils.assertSuccess(MapperExecutor.insertList(participantMapper, participants));

        logger.info("Adding stats of participants in the match {}...", gameId);
        Map<Integer, Long> num2Id = new HashMap<>();
        for (ParticipantDo participant : participants) {
            num2Id.put(participant.getParticipantNum(), participant.getId());
        }
        for (Map.Entry<Integer, ParticipantStatsDo> entry : num2Stats.entrySet()) {
            entry.getValue().setParticipantId(num2Id.get(entry.getKey()));
        }
        ResultUtils.assertSuccess(MapperExecutor.insertList(participantStatsMapper, new ArrayList<>(num2Stats.values())));

        logger.info("Adding frames of participants in the match {}...", gameId);
        List<ParticipantFrameDo> participantFrames = new ArrayList<>();
        for (MatchFrameDto frame : frames) {
            for (Map.Entry<String, MatchParticipantFrameDto> entry : frame.getParticipantFrames().entrySet()) {
                ParticipantFrameDo participantFrameDo = ObjectTransfer.transferDto(entry.getValue(), ParticipantFrameDo.class);
                Integer num = Integer.parseInt(entry.getKey());

                participantFrameDo.setParticipantId(num2Id.get(num));
                participantFrameDo.setTimeline(frame.getTimestamp());

                participantFrames.add(participantFrameDo);
            }
        }
        ResultUtils.assertSuccess(MapperExecutor.insertList(participantFrameMapper, participantFrames));

        MatchDo matchDo = ObjectTransfer.transferDto(matchExtDto, MatchDo.class);
        matchDo.setFrameInterval(timelineDto.getFrameInterval());
        int count = matchMapper.insert(matchDo);
        if (count != 1) {
            logger.error("Failed to insert match {}.", matchDo);
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }

        logger.info("Added the match {}.", gameId);
        return ResultUtils.success();
    }

    @Autowired
    public void setMatchFrameMapper(MatchFrameMapper matchFrameMapper) {
        this.matchFrameMapper = matchFrameMapper;
    }

    @Autowired
    public void setTeamStatsMapper(TeamStatsMapper teamStatsMapper) {
        this.teamStatsMapper = teamStatsMapper;
    }

    @Autowired
    public void setMatchMapper(MatchMapper matchMapper) {
        this.matchMapper = matchMapper;
    }

    @Autowired
    public void setParticipantMapper(ParticipantMapper participantMapper) {
        this.participantMapper = participantMapper;
    }

    @Autowired
    public void setParticipantStatsMapper(ParticipantStatsMapper participantStatsMapper) {
        this.participantStatsMapper = participantStatsMapper;
    }

    @Autowired
    public void setParticipantFrameMapper(ParticipantFrameMapper participantFrameMapper) {
        this.participantFrameMapper = participantFrameMapper;
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
