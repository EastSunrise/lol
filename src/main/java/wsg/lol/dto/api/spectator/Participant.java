package wsg.lol.dto.api.spectator;

/**
 * @author King
 * @date 2019/2/12
 */
public class Participant {

    /**
     * The ID of the profile icon used by this participant
     */
    private long profileIconId;

    /**
     * The ID of the champion played by this participant
     */
    private long championId;

    /**
     * The summoner name of this participant
     */
    private String summonerName;

    /**
     * Flag indicating whether or not this participant is a bot
     */
    private boolean bot;

    /**
     * The ID of the second summoner spell used by this participant
     */
    private long spell2Id;

    /**
     * The team ID of this participant, indicating the participant's team
     */
    private long teamId;

    /**
     * The ID of the first summoner spell used by this participant
     */
    private long spell1Id;

    public long getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(long profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public long getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(long spell2Id) {
        this.spell2Id = spell2Id;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(long spell1Id) {
        this.spell1Id = spell1Id;
    }
}
