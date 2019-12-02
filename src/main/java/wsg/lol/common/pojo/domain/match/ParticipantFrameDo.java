package wsg.lol.common.pojo.domain.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.annotation.Flatten;
import wsg.lol.common.base.BaseDo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for frames of participants in the match
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "m_participant_frame")
public class ParticipantFrameDo extends BaseDo {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column
    private Long relatedId;

    @Column
    private Integer timeline;

    @Column
    @Flatten
    private Integer positionX;

    @Column
    @Flatten
    private Integer positionY;

    @Column
    private Integer currentGold;

    @Column
    private Integer totalGold;

    @Column
    private Integer level;

    @Column
    private Integer xp;

    @Column
    private Integer minionsKilled;

    @Column
    private Integer jungleMinionsKilled;

    @Column
    private Integer dominionScore;

    @Column
    private Integer teamScore;
}