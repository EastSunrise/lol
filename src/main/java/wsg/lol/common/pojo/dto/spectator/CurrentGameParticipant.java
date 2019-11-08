package wsg.lol.common.pojo.dto.spectator;

import java.util.List;

/**
 * @author EastSunrise
 */
public class CurrentGameParticipant {

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
     * List of Game Customizations
     */
    private List<GameCustomizationObject> gameCustomizationObjects;

    /**
     * Flag indicating whether or not this participant is a bot
     */
    private boolean bot;

    /**
     * Perks/Runes Reforged Information
     */
    private Perks perks;

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

    /**
     * The encrypted summoner ID of this participant
     */
    private String summonerId;

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

    public List<GameCustomizationObject> getGameCustomizationObjects() {
        return gameCustomizationObjects;
    }

    public void setGameCustomizationObjects(List<GameCustomizationObject> gameCustomizationObjects) {
        this.gameCustomizationObjects = gameCustomizationObjects;
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public Perks getPerks() {
        return perks;
    }

    public void setPerks(Perks perks) {
        this.perks = perks;
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

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }
}
