package wsg.lol.common;

public class ParticipantDo {
    private Long id;

    private Long gameId;

    private Integer participantId;

    private String summonerId;

    private Integer championId;

    private Byte teamId;

    private Byte lane;

    private Byte role;

    private String spell1Id;

    private String spell2Id;

    private Byte highestAchievedSeasonTier;

    private String timeline;

    private String stat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId == null ? null : summonerId.trim();
    }

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public Byte getTeamId() {
        return teamId;
    }

    public void setTeamId(Byte teamId) {
        this.teamId = teamId;
    }

    public Byte getLane() {
        return lane;
    }

    public void setLane(Byte lane) {
        this.lane = lane;
    }

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    public String getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(String spell1Id) {
        this.spell1Id = spell1Id == null ? null : spell1Id.trim();
    }

    public String getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(String spell2Id) {
        this.spell2Id = spell2Id == null ? null : spell2Id.trim();
    }

    public Byte getHighestAchievedSeasonTier() {
        return highestAchievedSeasonTier;
    }

    public void setHighestAchievedSeasonTier(Byte highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline == null ? null : timeline.trim();
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat == null ? null : stat.trim();
    }
}