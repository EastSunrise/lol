package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.MatchLaneEnum;
import wsg.lol.common.enums.match.MatchRoleEnum;

import java.util.Map;

/**
 * DTO for timeline of the participant.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParticipantTimelineDto extends BaseDto {

    private Integer participantId;

    /**
     * Creeps for a specified period.
     */
    private Map<String, Double> creepsPerMinDeltas;

    /**
     * Experience change for a specified period.
     */
    private Map<String, Double> xpPerMinDeltas;

    /**
     * Gold for a specified period.
     */
    private Map<String, Double> goldPerMinDeltas;

    /**
     * Creep score difference versus the calculated lane opponent(s) for a specified period.
     */
    private Map<String, Double> csDiffPerMinDeltas;

    /**
     * Experience difference versus the calculated lane opponent(s) for a specified period.
     */
    private Map<String, Double> xpDiffPerMinDeltas;

    /**
     * Damage taken for a specified period.
     */
    private Map<String, Double> damageTakenPerMinDeltas;

    /**
     * Damage taken difference versus the calculated lane opponent(s) for a specified period.
     */
    private Map<String, Double> damageTakenDiffPerMinDeltas;

    /**
     * Participant's calculated role. (Legal values: DUO, NONE, SOLO, DUO_CARRY, DUO_SUPPORT)
     */
    private MatchRoleEnum role;

    /**
     * Participant's calculated lane. MID and BOT are legacy values. (Legal values: MID, MIDDLE, TOP, JUNGLE, BOT,
     * BOTTOM)
     */
    private MatchLaneEnum lane;
}
