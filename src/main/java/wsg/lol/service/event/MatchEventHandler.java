package wsg.lol.service.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.domain.match.*;
import wsg.lol.common.pojo.domain.system.EventDo;
import wsg.lol.common.pojo.dto.match.*;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.api.client.ApiClient;
import wsg.lol.dao.api.impl.MatchV4;
import wsg.lol.dao.mybatis.mapper.region.match.*;
import wsg.lol.service.common.ServiceExecutor;
import wsg.lol.service.intf.EventService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handler for events of type {@link EventTypeEnum#Match}
 *
 * @author Kingen
 */
@Service("MatchEventHandler")
public class MatchEventHandler implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(MatchEventHandler.class);

    private MatchV4 matchV4;

    private MatchFrameMapper matchFrameMapper;

    private TeamStatsMapper teamStatsMapper;

    private MatchMapper matchMapper;

    private ParticipantMapper participantMapper;

    private ParticipantStatsMapper participantStatsMapper;

    private ParticipantFrameMapper participantFrameMapper;

    private EventService eventService;

    private ApiClient apiClient;

    @Override
    @Transactional
    public Result handle(EventDo event) {
        long gameId = Long.parseLong(event.getContext());
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
        ServiceExecutor.insertList(matchFrameMapper, frameDoList).assertSuccess();

        logger.info("Adding stats of teams in the match {}...", gameId);
        List<TeamStatsDo> teams = new ArrayList<>();
        List<TeamStatsDto> teamStatsDtoList = matchExtDto.getTeams();
        for (TeamStatsDto teamStatsDto : teamStatsDtoList) {
            TeamStatsDo teamStatsDo = ObjectTransfer.transferDto(teamStatsDto, TeamStatsDo.class);
            teamStatsDo.setGameId(gameId);
            teams.add(teamStatsDo);
        }
        ServiceExecutor.insertList(teamStatsMapper, teams).assertSuccess();

        logger.info("Adding participants of the match {}...", gameId);
        List<ParticipantDo> participants = new ArrayList<>();
        Map<Integer, ParticipantStatsDo> num2Stats = new HashMap<>();
        Map<Integer, PlayerDto> num2Player = new HashMap<>();
        Map<String, String> events = new HashMap<>();
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

            participantDo.setSummonerName(playerDto.getSummonerName());
            participantDo.setSummonerId(playerDto.getSummonerId());
            participantDo.setAccountId(playerDto.getAccountId());
            participantDo.setCurrentAccountId(playerDto.getCurrentAccountId());
            participantDo.setCurrentPlatformId(playerDto.getCurrentPlatformId());

            participantDo.setRole(participantDto.getTimeline().getRole());
            participantDo.setLane(participantDto.getTimeline().getLane());

            participants.add(participantDo);

            num2Stats.put(num, ObjectTransfer.transferDto(participantDto.getStats(), ParticipantStatsDo.class));

            events.put(participantDo.getSummonerId(), apiClient.getUsername());
        }
        eventService.insertEvents(EventTypeEnum.Summoner, events).assertSuccess();
        ServiceExecutor.insertList(participantMapper, participants).assertSuccess();

        logger.info("Adding stats of participants in the match {}...", gameId);
        Map<Integer, Long> num2Id = new HashMap<>();
        for (ParticipantDo participant : participants) {
            num2Id.put(participant.getParticipantNum(), participant.getId());
        }
        for (Map.Entry<Integer, ParticipantStatsDo> entry : num2Stats.entrySet()) {
            entry.getValue().setParticipantId(num2Id.get(entry.getKey()));
        }
        ServiceExecutor.insertList(participantStatsMapper, new ArrayList<>(num2Stats.values())).assertSuccess();

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
        ServiceExecutor.insertList(participantFrameMapper, participantFrames).assertSuccess();

        MatchDo matchDo = ObjectTransfer.transferDto(matchExtDto, MatchDo.class);
        matchDo.setFrameInterval(timelineDto.getFrameInterval());
        int count = matchMapper.insert(matchDo);
        if (count != 1) {
            logger.error("Failed to insert match {}.", matchDo);
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }

        eventService.updateStatus(EventTypeEnum.Match, gameId, EventStatusEnum.Unfinished, EventStatusEnum.Finished).assertSuccess();

        logger.info("Added the match {}.", gameId);
        return ResultUtils.success();
    }

    @Autowired
    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
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
    public void setMatchV4(MatchV4 matchV4) {
        this.matchV4 = matchV4;
    }
}
