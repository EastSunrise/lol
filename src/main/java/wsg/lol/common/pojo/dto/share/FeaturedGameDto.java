package wsg.lol.common.pojo.dto.share;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.enums.match.GameTypeEnum;
import wsg.lol.common.enums.match.MatchQueueEnum;
import wsg.lol.common.enums.match.TeamIdEnum;
import wsg.lol.common.enums.share.MapEnum;
import wsg.lol.common.enums.system.RegionEnum;

import java.util.Date;
import java.util.List;

/**
 * DTO for the featured game.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeaturedGameDto extends BaseDto {

    /**
     * The ID of the game
     */
    private Long gameId;

    /**
     * The game start time represented in epoch milliseconds
     */
    private Date gameStartTime;

    /**
     * The ID of the platform on which the game is being played
     */
    private RegionEnum platformId;

    /**
     * The game mode (Legal values: CLASSIC, ODIN, ARAM, TUTORIAL, ONEFORALL, ASCENSION, FIRSTBLOOD, KINGPORO)
     */
    private GameModeEnum gameMode;

    /**
     * The ID of the map
     */
    private MapEnum mapId;

    /**
     * The game type (Legal values: CUSTOM_GAME, MATCHED_GAME, TUTORIAL_GAME)
     */
    private GameTypeEnum gameType;

    /**
     * Banned champion information
     */
    private List<BannedChampion> bannedChampions;

    /**
     * The observer information
     */
    private Observer observers;

    /**
     * The participant information
     */
    private List<FeaturedParticipantDto> participants;

    /**
     * The amount of time in seconds that has passed since the game started
     */
    private Integer gameLength;

    /**
     * The queue type (queue types are documented on the Game Constants page)
     */
    private MatchQueueEnum gameQueueConfigId;

    /**
     * DTO for banned champions of the team.
     */
    @Data
    static class BannedChampion {

        /**
         * The turn during which the champion was banned
         */
        private Integer pickTurn;

        /**
         * The ID of the banned champion
         */
        private Integer championId;

        /**
         * The ID of the team that banned the champion
         */
        private TeamIdEnum teamId;
    }

    /**
     * DTO for participants of the featured game.
     */
    @Data
    static class FeaturedParticipantDto {

        /**
         * The ID of the profile icon used by this participant
         */
        private Integer profileIconId;

        /**
         * The ID of the champion played by this participant
         */
        private Integer championId;

        /**
         * The summoner name of this participant
         */
        private String summonerName;

        /**
         * List of Game Customizations
         */
        private List<CustomObject> gameCustomizationObjects;

        /**
         * Flag indicating whether or not this participant is a bot
         */
        private Boolean bot;

        /**
         * Perks/Runes Reforged Information
         */
        private Perks perks;

        /**
         * The ID of the second summoner spell used by this participant
         */
        private Integer spell2Id;

        /**
         * The team ID of this participant, indicating the participant's team
         */
        private Integer teamId;

        /**
         * The ID of the first summoner spell used by this participant
         */
        private Integer spell1Id;

        /**
         * The encrypted summoner ID of this participant
         */
        private String summonerId;

        private Integer skinIndex;

        /**
         * DTO for runes of the featured participant.
         */
        @Data
        static class Perks {

            /**
             * Primary runes path
             */
            private Long perkStyle;

            /**
             * IDs of the perks/runes assigned.
             */
            private List<Long> perkIds;

            /**
             * Secondary runes path
             */
            private Long perkSubStyle;
        }
    }

    /**
     * DTO for custom objects in the game.
     */
    @Data
    static class CustomObject {

        /**
         * Category identifier for Game Customization
         */
        private String category;

        /**
         * Game Customization content
         */
        private String content;
    }

    /**
     * DTO for observers of the game.
     */
    @Data
    static class Observer {

        /**
         * Key used to decrypt the spectator grid game data for playback
         */
        private String encryptionKey;
    }
}
