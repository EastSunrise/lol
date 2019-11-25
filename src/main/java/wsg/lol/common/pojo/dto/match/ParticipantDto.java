package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.TeamIdEnum;
import wsg.lol.common.enums.rank.TierEnum;

import java.util.List;

/**
 * Bean for each participant in the match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParticipantDto extends BaseDto {

    /**
     * Participant statistics.
     */
    private ParticipantStatsDto stats;
    private Integer participantId;

    /**
     * List of legacy Rune information. Not included for match played with Runes Reforged.
     */
    private List<RuneReferenceDto> runes;

    /**
     * Participant timeline data.
     */
    private ParticipantTimelineDto timeline;

    /**
     * 100 for blue side. 200 for red side.
     */
    private TeamIdEnum teamId;

    /**
     * First Summoner Spell id.
     */
    private Integer spell1Id;

    /**
     * Second Summoner Spell id.
     */
    private Integer spell2Id;

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

    private Integer championId;
}
