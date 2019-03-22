package wsg.lol.pojo.dto.api.match;

import java.util.List;

/**
 * @author King
 * @date 2019/2/12
 */
public class MatchEventDto {

    private String eventType;
    private String towerType;
    private int teamId;
    private String ascendedType;
    private int killerId;
    private String levelUpType;
    private String pointCaptured;
    private List<Integer> assistingParticipantIds;
    private String wardType;
    private String monsterType;

    /**
     * (Legal values: CHAMPION_KILL, WARD_PLACED, WARD_KILL, BUILDING_KILL, ELITE_MONSTER_KILL, ITEM_PURCHASED,
     * ITEM_SOLD, ITEM_DESTROYED, ITEM_UNDO, SKILL_LEVEL_UP, ASCENDED_EVENT, CAPTURE_POINT, PORO_KING_SUMMON)
     */
    private String type;
    private int skillSlot;
    private int victimId;
    private long timestamp;
    private int afterId;
    private String monsterSubType;
    private String laneType;
    private int itemId;
    private int participantId;
    private String buildingType;
    private int creatorId;
    private MatchPositionDto position;
    private int beforeId;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTowerType() {
        return towerType;
    }

    public void setTowerType(String towerType) {
        this.towerType = towerType;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getAscendedType() {
        return ascendedType;
    }

    public void setAscendedType(String ascendedType) {
        this.ascendedType = ascendedType;
    }

    public int getKillerId() {
        return killerId;
    }

    public void setKillerId(int killerId) {
        this.killerId = killerId;
    }

    public String getLevelUpType() {
        return levelUpType;
    }

    public void setLevelUpType(String levelUpType) {
        this.levelUpType = levelUpType;
    }

    public String getPointCaptured() {
        return pointCaptured;
    }

    public void setPointCaptured(String pointCaptured) {
        this.pointCaptured = pointCaptured;
    }

    public List<Integer> getAssistingParticipantIds() {
        return assistingParticipantIds;
    }

    public void setAssistingParticipantIds(List<Integer> assistingParticipantIds) {
        this.assistingParticipantIds = assistingParticipantIds;
    }

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSkillSlot() {
        return skillSlot;
    }

    public void setSkillSlot(int skillSlot) {
        this.skillSlot = skillSlot;
    }

    public int getVictimId() {
        return victimId;
    }

    public void setVictimId(int victimId) {
        this.victimId = victimId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getAfterId() {
        return afterId;
    }

    public void setAfterId(int afterId) {
        this.afterId = afterId;
    }

    public String getMonsterSubType() {
        return monsterSubType;
    }

    public void setMonsterSubType(String monsterSubType) {
        this.monsterSubType = monsterSubType;
    }

    public String getLaneType() {
        return laneType;
    }

    public void setLaneType(String laneType) {
        this.laneType = laneType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public MatchPositionDto getPosition() {
        return position;
    }

    public void setPosition(MatchPositionDto position) {
        this.position = position;
    }

    public int getBeforeId() {
        return beforeId;
    }

    public void setBeforeId(int beforeId) {
        this.beforeId = beforeId;
    }
}
