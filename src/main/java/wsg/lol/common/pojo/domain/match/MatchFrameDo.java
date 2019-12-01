package wsg.lol.common.pojo.domain.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.annotation.Flatten;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.match.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * DO for frames of the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "m_match_frame")
public class MatchFrameDo extends BaseDo {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column
    private Long gameId;

    @Column
    private Date timeline;

    @Column
    private Date timing;

    @Column
    private MatchEventTypeEnum type;

    @Column
    private Integer participantId;

    @Column
    private Integer skillSlot;

    @Column
    private LevelUpTypeEnum levelUpType;

    @Column
    private Integer itemId;

    @Column
    private Integer afterId;

    @Column
    private Integer beforeId;

    @Column
    private WardTypeEnum wardType;

    @Column
    private Integer creatorId;

    @Column
    @Flatten
    private Integer positionX;

    @Column
    @Flatten
    private Integer positionY;

    @Column
    private Integer killerId;

    @Column
    private Integer victimId;

    @Column
    private List<Integer> assistingParticipantIds;

    @Column
    private MonsterTypeEnum monsterType;

    @Column
    private MonsterSubTypeEnum monsterSubType;

    @Column
    private TeamIdEnum teamId;

    @Column
    private BuildingTypeEnum buildingType;

    @Column
    private LaneTypeEnum laneType;

    @Column
    private TowerTypeEnum towerType;
}