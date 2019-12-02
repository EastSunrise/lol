package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.*;
import wsg.lol.common.pojo.serialize.CustomEnumDeserializer;

import java.util.List;

/**
 * DTO for events in the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchEventDto extends BaseDto {

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private MatchEventTypeEnum type;
    private Integer timestamp;
    private Integer participantId;

    private Integer skillSlot;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private LevelUpTypeEnum levelUpType;

    private Integer itemId;
    private Integer afterId;
    private Integer beforeId;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private WardTypeEnum wardType;

    private Integer creatorId;

    private MatchPositionDto position;

    private Integer killerId;
    private Integer victimId;
    private List<Integer> assistingParticipantIds;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private MonsterTypeEnum monsterType;
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private MonsterSubTypeEnum monsterSubType;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private TeamIdEnum teamId;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private BuildingTypeEnum buildingType;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private LaneTypeEnum laneType;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private TowerTypeEnum towerType;
}
