package wsg.lol.common.pojo.dto.spectator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.List;

/**
 * DTO for information of current game.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CurrentGameInfo extends BaseDto {

    /**
     * The ID of the game
     */
    private long gameId;

    /**
     * The game start time represented in epoch milliseconds
     */
    private long gameStartTime;

    /**
     * The ID of the platform on which the game is being played
     */
    private String platformId;

    /**
     * The game mode
     */
    private String gameMode;

    /**
     * The ID of the map
     */
    private long mapId;

    /**
     * The game type
     */
    private String gameType;

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
    private List<CurrentGameParticipant> participants;

    /**
     * The amount of time in seconds that has passed since the game started
     */
    private long gameLength;

    /**
     * The queue type (queue types are documented on the Game Constants page)
     */
    private long gameQueueConfigId;
}
