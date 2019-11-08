package wsg.lol.common.pojo.dto.match;

import wsg.lol.common.pojo.base.BaseDto;

import java.util.Map;

/**
 * @author EastSunrise
 */
public class ParticipantTimelineDto extends BaseDto {

    /**
     * Participant's calculated lane. MID and BOT are legacy values. (Legal values: MID, MIDDLE, TOP, JUNGLE, BOT,
     * BOTTOM)
     */
    private String lane;
    private int participantId;

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

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public Map<String, Double> getCsDiffPerMinDeltas() {
        return csDiffPerMinDeltas;
    }

    public void setCsDiffPerMinDeltas(Map<String, Double> csDiffPerMinDeltas) {
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
    }

    public Map<String, Double> getGoldPerMinDeltas() {
        return goldPerMinDeltas;
    }

    public void setGoldPerMinDeltas(Map<String, Double> goldPerMinDeltas) {
        this.goldPerMinDeltas = goldPerMinDeltas;
    }

    public Map<String, Double> getXpDiffPerMinDeltas() {
        return xpDiffPerMinDeltas;
    }

    public void setXpDiffPerMinDeltas(Map<String, Double> xpDiffPerMinDeltas) {
        this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
    }

    public Map<String, Double> getCreepsPerMinDeltas() {
        return creepsPerMinDeltas;
    }

    public void setCreepsPerMinDeltas(Map<String, Double> creepsPerMinDeltas) {
        this.creepsPerMinDeltas = creepsPerMinDeltas;
    }

    public Map<String, Double> getXpPerMinDeltas() {
        return xpPerMinDeltas;
    }

    public void setXpPerMinDeltas(Map<String, Double> xpPerMinDeltas) {
        this.xpPerMinDeltas = xpPerMinDeltas;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, Double> getDamageTakenDiffPerMinDeltas() {
        return damageTakenDiffPerMinDeltas;
    }

    public void setDamageTakenDiffPerMinDeltas(Map<String, Double> damageTakenDiffPerMinDeltas) {
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
    }

    public Map<String, Double> getDamageTakenPerMinDeltas() {
        return damageTakenPerMinDeltas;
    }

    public void setDamageTakenPerMinDeltas(Map<String, Double> damageTakenPerMinDeltas) {
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
    }
}
