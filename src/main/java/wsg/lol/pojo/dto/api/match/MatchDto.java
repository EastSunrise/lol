package wsg.lol.pojo.dto.api.match;

import org.springframework.data.annotation.Id;
import wsg.lol.pojo.base.BaseDto;
import wsg.lol.pojo.base.IJson;
import wsg.lol.pojo.enums.impl.code.TierEnum;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 */
public class MatchDto extends BaseDto implements IJson {

    private int seasonId;
    private int queueId;

    @Id
    private long gameId;
    private String gameVersion;
    private String platformId;
    private String gameMode;
    private int mapId;
    private String gameType;
    private int gameDuration;
    private long gameCreation;
    private List<ParticipantIdentityDto> participantIdentities;
    private List<TeamStatsDto> teams;
    private List<ParticipantDto> participants;

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public List<ParticipantIdentityDto> getParticipantIdentities() {
        return participantIdentities;
    }

    public void setParticipantIdentities(List<ParticipantIdentityDto> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    public List<TeamStatsDto> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamStatsDto> teams) {
        this.teams = teams;
    }

    public List<ParticipantDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDto> participants) {
        this.participants = participants;
    }

    /**
     * @author King
     */
    public static class ParticipantDto extends BaseDto {

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
        private List<MasteryDto> masteries;

        /**
         * Highest ranked tier achieved for the previous season in a specific subset of queueIds, if any, otherwise
         * null.
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

        public List<MasteryDto> getMasteries() {
            return masteries;
        }

        public void setMasteries(List<MasteryDto> masteries) {
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

        /**
         * @author King
         */
        public static class MasteryDto extends BaseDto {

            private int masteryId;
            private int rank;

            public int getMasteryId() {
                return masteryId;
            }

            public void setMasteryId(int masteryId) {
                this.masteryId = masteryId;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }
        }
    }

    /**
     * @author King
     */
    public static class TeamStatsDto extends BaseDto {

        /**
         * Flag indicating whether or not the team scored the first Dragon kill.
         */
        private boolean firstDragon;

        /**
         * Flag indicating whether or not the team destroyed the first inhibitor.
         */
        private boolean firstInhibitor;

        /**
         * If match queueId has a draft, contains banned champion data, otherwise empty.
         */
        private List<TeamBansDto> bans;

        /**
         * Number of times the team killed Baron.
         */
        private int baronKills;

        /**
         * Flag indicating whether or not the team scored the first Rift Herald kill.
         */
        private boolean firstRiftHerald;

        /**
         * Flag indicating whether or not the team scored the first Baron kill.
         */
        private boolean firstBaron;

        /**
         * Number of times the team killed Rift Herald.
         */
        private int riftHeraldKills;

        /**
         * Flag indicating whether or not the team scored the first blood.
         */
        private boolean firstBlood;

        /**
         * 100 for blue side. 200 for red side.
         */
        private int teamId;

        /**
         * Flag indicating whether or not the team destroyed the first tower.
         */
        private boolean firstTower;

        /**
         * Number of times the team killed Vilemaw.
         */
        private int vilemawKills;

        /**
         * Number of inhibitors the team destroyed.
         */
        private int inhibitorKills;

        /**
         * Number of towers the team destroyed.
         */
        private int towerKills;

        /**
         * For Dominion match, specifies the points the team had at game end.
         */
        private int dominionVictoryScore;

        /**
         * String indicating whether or not the team won. There are only two values visibile in public match history.
         * (Legal
         * values: Fail, Win)
         */
        private String win;

        /**
         * Number of times the team killed Dragon.
         */
        private int dragonKills;

        public boolean isFirstDragon() {
            return firstDragon;
        }

        public void setFirstDragon(boolean firstDragon) {
            this.firstDragon = firstDragon;
        }

        public boolean isFirstInhibitor() {
            return firstInhibitor;
        }

        public void setFirstInhibitor(boolean firstInhibitor) {
            this.firstInhibitor = firstInhibitor;
        }

        public List<TeamBansDto> getBans() {
            return bans;
        }

        public void setBans(List<TeamBansDto> bans) {
            this.bans = bans;
        }

        public int getBaronKills() {
            return baronKills;
        }

        public void setBaronKills(int baronKills) {
            this.baronKills = baronKills;
        }

        public boolean isFirstRiftHerald() {
            return firstRiftHerald;
        }

        public void setFirstRiftHerald(boolean firstRiftHerald) {
            this.firstRiftHerald = firstRiftHerald;
        }

        public boolean isFirstBaron() {
            return firstBaron;
        }

        public void setFirstBaron(boolean firstBaron) {
            this.firstBaron = firstBaron;
        }

        public int getRiftHeraldKills() {
            return riftHeraldKills;
        }

        public void setRiftHeraldKills(int riftHeraldKills) {
            this.riftHeraldKills = riftHeraldKills;
        }

        public boolean isFirstBlood() {
            return firstBlood;
        }

        public void setFirstBlood(boolean firstBlood) {
            this.firstBlood = firstBlood;
        }

        public int getTeamId() {
            return teamId;
        }

        public void setTeamId(int teamId) {
            this.teamId = teamId;
        }

        public boolean isFirstTower() {
            return firstTower;
        }

        public void setFirstTower(boolean firstTower) {
            this.firstTower = firstTower;
        }

        public int getVilemawKills() {
            return vilemawKills;
        }

        public void setVilemawKills(int vilemawKills) {
            this.vilemawKills = vilemawKills;
        }

        public int getInhibitorKills() {
            return inhibitorKills;
        }

        public void setInhibitorKills(int inhibitorKills) {
            this.inhibitorKills = inhibitorKills;
        }

        public int getTowerKills() {
            return towerKills;
        }

        public void setTowerKills(int towerKills) {
            this.towerKills = towerKills;
        }

        public int getDominionVictoryScore() {
            return dominionVictoryScore;
        }

        public void setDominionVictoryScore(int dominionVictoryScore) {
            this.dominionVictoryScore = dominionVictoryScore;
        }

        public String getWin() {
            return win;
        }

        public void setWin(String win) {
            this.win = win;
        }

        public int getDragonKills() {
            return dragonKills;
        }

        public void setDragonKills(int dragonKills) {
            this.dragonKills = dragonKills;
        }
    }

    /**
     * @author King
     */
    public static class ParticipantIdentityDto extends BaseDto {

        /**
         * Player information.
         */
        private PlayerDto player;
        private int participantId;

        public PlayerDto getPlayer() {
            return player;
        }

        public void setPlayer(PlayerDto player) {
            this.player = player;
        }

        public int getParticipantId() {
            return participantId;
        }

        public void setParticipantId(int participantId) {
            this.participantId = participantId;
        }
    }
}
