package wsg.lol.common.pojo.dto.spectator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.List;

/**
 * DTO for participants of the featured game.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
class FeaturedParticipantDto extends BaseDto {

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
}
