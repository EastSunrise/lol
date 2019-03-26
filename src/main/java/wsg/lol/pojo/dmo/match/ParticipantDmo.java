package wsg.lol.pojo.dmo.match;


import wsg.lol.common.annotation.JsonKey;
import wsg.lol.pojo.base.ApiBean;
import wsg.lol.pojo.enums.impl.code.MatchLaneEnum;
import wsg.lol.pojo.enums.impl.code.MatchRoleEnum;
import wsg.lol.pojo.enums.impl.code.TierEnum;

public class ParticipantDmo extends ApiBean {

    private Long id;
    private Long gameId;
    private Integer participantId;
    private String summonerId;
    private Integer championId;
    private Integer teamId;

    @JsonKey
    private MatchLaneEnum timeline_lane;

    @JsonKey
    private MatchRoleEnum timeline_role;

    private String spell1Id;
    private String spell2Id;
    private TierEnum highestAchievedSeasonTier;

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
        this.summonerId = summonerId;
    }


    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(String spell1Id) {
        this.spell1Id = spell1Id;
    }

    public MatchLaneEnum getTimeline_lane() {
        return timeline_lane;
    }

    public void setTimeline_lane(MatchLaneEnum timeline_lane) {
        this.timeline_lane = timeline_lane;
    }

    public MatchRoleEnum getTimeline_role() {
        return timeline_role;
    }

    public void setTimeline_role(MatchRoleEnum timeline_role) {
        this.timeline_role = timeline_role;
    }

    public TierEnum getHighestAchievedSeasonTier() {
        return highestAchievedSeasonTier;
    }

    public void setHighestAchievedSeasonTier(TierEnum highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    public String getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(String spell2Id) {
        this.spell2Id = spell2Id;
    }
}
