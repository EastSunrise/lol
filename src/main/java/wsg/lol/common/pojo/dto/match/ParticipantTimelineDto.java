package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.rank.MatchLaneEnum;

import java.util.Map;

/**
 * Bean for timeline of the participant.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParticipantTimelineDto extends BaseDto {

    /**
     * Participant's calculated lane. MID and BOT are legacy values. (Legal values: MID, MIDDLE, TOP, JUNGLE, BOT,
     * BOTTOM)
     */
    private MatchLaneEnum lane;
    private Integer participantId;

    /**
     * Creep score difference versus the calculated lane opponent(s) for a specified period.
     */
    private Map<String, Double> csDiffPerMinDeltas;

    /**
     * Gold for a specified period.
     */
    private Map<String, Double> goldPerMinDeltas;

    /**
     * Experience difference versus the calculated lane opponent(s) for a specified period.
     */
    private Map<String, Double> xpDiffPerMinDeltas;

    /**
     * Creeps for a specified period.
     */
    private Map<String, Double> creepsPerMinDeltas;

    /**
     * Experience change for a specified period.
     */
    private Map<String, Double> xpPerMinDeltas;

    /**
     * Participant's calculated role. (Legal values: DUO, NONE, SOLO, DUO_CARRY, DUO_SUPPORT)
     */
    private String role;

    /**
     * Damage taken difference versus the calculated lane opponent(s) for a specified period.
     */
    private Map<String, Double> damageTakenDiffPerMinDeltas;

    /**
     * Damage taken for a specified period.
     */
    private Map<String, Double> damageTakenPerMinDeltas;
}
