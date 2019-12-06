package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.*;

import java.sql.Time;
import java.util.List;

/**
 * DTO for events in the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchEventDto extends BaseDto {

    private MatchEventTypeEnum type;
    private Time timestamp;
    private Integer participantId;

    private Integer skillSlot;

    private LevelUpTypeEnum levelUpType;

    private Integer itemId;
    private Integer afterId;
    private Integer beforeId;

    private WardTypeEnum wardType;

    private Integer creatorId;

    private MatchPositionDto position;

    private Integer killerId;
    private Integer victimId;
    private List<Integer> assistingParticipantIds;

    private MonsterTypeEnum monsterType;
    private MonsterSubTypeEnum monsterSubType;

    private TeamIdEnum teamId;

    private BuildingTypeEnum buildingType;

    private LaneTypeEnum laneType;

    private TowerTypeEnum towerType;
}
