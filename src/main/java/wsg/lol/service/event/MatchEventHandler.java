package wsg.lol.service.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import wsg.lol.common.annotation.Performance;
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
import wsg.lol.dao.api.impl.MatchV4;
import wsg.lol.dao.mybatis.mapper.region.match.*;
import wsg.lol.service.common.MapperExecutor;
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

    private TransactionTemplate transactionTemplate;

    private MatchV4 matchV4;

    private MatchMapper matchMapper;

    private MatchFrameMapper matchFrameMapper;

    private TeamStatsMapper teamStatsMapper;

    private ParticipantMapper participantMapper;

    private ParticipantStatsMapper participantStatsMapper;

    private ParticipantFrameMapper participantFrameMapper;

    private EventService eventService;

    @Override
    @Performance
    public Result handle(List<? extends EventDo> events) {
        final int[] success = {0};
        for (EventDo event : events) {
            long gameId = Long.parseLong(event.getId());
            try {
                transactionTemplate.execute(transactionStatus -> {
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
                    List<ParticipantDto> participantDtoList = matchExtDto.getParticipants();
                    Map<Integer, ParticipantDto> map = new HashMap<>();
                    for (ParticipantDto participantDto : participantDtoList) {
                        map.put(participantDto.getParticipantId(), participantDto);
                    }
                    List<ParticipantIdentityDto> participantIdentities = matchExtDto.getParticipantIdentities();
                    Map<Integer, ParticipantStatsDo> statsMap = new HashMap<>();
                    for (ParticipantIdentityDto participantIdentity : participantIdentities) {
                        ParticipantDo participantDo = ObjectTransfer.transferDto(participantIdentity.getPlayer(), ParticipantDo.class);
                        int participantId = participantIdentity.getParticipantId();
                        ParticipantDto participantDto = map.get(participantId);
                        ParticipantTimelineDto participantTimelineDto = participantDto.getTimeline();
                        statsMap.put(participantId, ObjectTransfer.transferDto(participantDto.getStats(), ParticipantStatsDo.class));

                        participantDo.setGameId(gameId);
                        participantDo.setParticipantId(participantId);
                        participantDo.generateId();

                        participantDo.setTeamId(participantDto.getTeamId());
                        participantDo.setChampionId(participantDto.getChampionId());
                        participantDo.setSpell1Id(participantDto.getSpell1Id());
                        participantDo.setSpell2Id(participantDto.getSpell2Id());
                        participantDo.setRole(participantTimelineDto.getRole());
                        participantDo.setLane(participantTimelineDto.getLane());

                        participants.add(participantDo);
                    }
                    ResultUtils.assertSuccess(MapperExecutor.insertList(participantMapper, participants));

                    logger.info("Adding stats of participants in the match {}...", gameId);
                    Map<Integer, Long> idMap = new HashMap<>();
                    for (ParticipantDo participant : participants) {
                        idMap.put(participant.getParticipantId(), participant.getId());
                    }
                    for (Map.Entry<Integer, ParticipantStatsDo> entry : statsMap.entrySet()) {
                        entry.getValue().setId(idMap.get(entry.getKey()));
                    }
                    ResultUtils.assertSuccess(MapperExecutor.insertList(participantStatsMapper, new ArrayList<>(statsMap.values())));

                    logger.info("Adding frames of participants in the match {}...", gameId);
                    List<ParticipantFrameDo> participantFrames = new ArrayList<>();
                    for (MatchFrameDto frame : frames) {
                        for (Map.Entry<String, MatchParticipantFrameDto> entry : frame.getParticipantFrames().entrySet()) {
                            ParticipantFrameDo participantFrameDo = ObjectTransfer.transferDto(entry.getValue(), ParticipantFrameDo.class);
                            Integer participantId = Integer.parseInt(entry.getKey());

                            participantFrameDo.setRelatedId(idMap.get(participantId));
                            participantFrameDo.setTimeline(frame.getTimestamp());

                            participantFrames.add(participantFrameDo);
                        }
                    }
                    ResultUtils.assertSuccess(MapperExecutor.insertList(participantFrameMapper, participantFrames));

                    MatchDo matchDo = ObjectTransfer.transferDto(matchExtDto, MatchDto.class, MatchDo.class);
                    matchDo.setFrameInterval(timelineDto.getFrameInterval());
                    int count = matchMapper.insert(matchDo);
                    if (count != 1) {
                        logger.error("Failed to insert match {}.", matchDo);
                        throw new AppException(ErrorCodeConst.DATABASE_ERROR);
                    }
                    logger.info("Added the match {}.", gameId);

                    Result result = eventService.updateStatus(EventTypeEnum.Match, gameId, EventStatusEnum.Unfinished, EventStatusEnum.Finished);
                    ResultUtils.assertSuccess(result);
                    logger.info("Succeed in handling the event of {}.", gameId);
                    success[0]++;
                    return ResultUtils.success();
                });
            } catch (AppException e) {
                logger.error("Failed to handle the event of the match {}", gameId);
                e.printStackTrace();
            }
        }

        logger.info("Events of matches done, {} succeeded, {} failed.", success[0], events.size() - success[0]);
        return ResultUtils.success();
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setMatchMapper(MatchMapper matchMapper) {
        this.matchMapper = matchMapper;
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
    public void setMatchV4(MatchV4 matchV4) {
        this.matchV4 = matchV4;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
