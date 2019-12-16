package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(name = "participantId")
    private Integer participantNum;

    private Integer skillSlot;

    private LevelUpTypeEnum levelUpType;

    private Integer itemId;
    private Integer afterId;
    private Integer beforeId;

    private WardTypeEnum wardType;

    @JSONField(name = "creatorId")
    private Integer creatorNum;

    private MatchPositionDto position;

    @JSONField(name = "killerId")
    private Integer killerNum;
    @JSONField(name = "victimId")
    private Integer victimNum;
    @JSONField(name = "assistingParticipantIds")
    private List<Integer> assistingParticipantNums;

    private MonsterTypeEnum monsterType;
    private MonsterSubTypeEnum monsterSubType;

    private TeamIdEnum teamId;

    private BuildingTypeEnum buildingType;

    private LaneTypeEnum laneType;

    private TowerTypeEnum towerType;
}
