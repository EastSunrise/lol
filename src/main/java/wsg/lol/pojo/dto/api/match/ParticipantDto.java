package wsg.lol.pojo.dto.api.match;

import wsg.lol.pojo.enums.impl.name.TierEnum;

import java.util.List;

/**
 * @author King
 * @date 2019/2/12
 */
public class ParticipantDto {

    /**
     * Participant statistics.
     */
    private ParticipantStatsDto stats;
    private int participantId;

    /**
     * List of legacy Rune information. Not included for match played with Runes Reforged.
     */
    private List<RuneDto> runes;

    /**
     * Participant timeline data.
     */
    private ParticipantTimelineDto timeline;

    /**
     * 100 for blue side. 200 for red side.
     */
    private int teamId;

    /**
     * Second Summoner Spell id.
     */
    private int spell2Id;

    /**
     * List of legacy Mastery information. Not included for match played with Runes Reforged.
     */
    private List<MatchMasteryDto> masteries;

    /**
     * Highest ranked tier achieved for the previous season in a specific subset of queueIds, if any, otherwise null.
     * Used to display border in game loading screen. Please refer to the Ranked Info documentation. (Legal values:
     * CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED)
     */
    private TierEnum highestAchievedSeasonTier;

    /**
     * First Summoner Spell id.
     */
    private int spell1Id;

    private int championId;

    public ParticipantStatsDto getStats() {
        return stats;
    }

    public void setStats(ParticipantStatsDto stats) {
        this.stats = stats;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public List<RuneDto> getRunes() {
        return runes;
    }

    public void setRunes(List<RuneDto> runes) {
        this.runes = runes;
    }

    public ParticipantTimelineDto getTimeline() {
        return timeline;
    }

    public void setTimeline(ParticipantTimelineDto timeline) {
        this.timeline = timeline;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(int spell2Id) {
        this.spell2Id = spell2Id;
    }

    public List<MatchMasteryDto> getMasteries() {
        return masteries;
    }

    public void setMasteries(List<MatchMasteryDto> masteries) {
        this.masteries = masteries;
    }

    public TierEnum getHighestAchievedSeasonTier() {
        return highestAchievedSeasonTier;
    }

    public void setHighestAchievedSeasonTier(TierEnum highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    public int getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(int spell1Id) {
        this.spell1Id = spell1Id;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }
}
