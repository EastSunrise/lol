package wsg.lol.common.pojo.dto.spectator;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.enums.match.GameTypeEnum;
import wsg.lol.common.enums.share.MapEnum;
import wsg.lol.common.enums.system.PlatformRoutingEnum;
import wsg.lol.common.pojo.serialize.CustomEnumDeserializer;

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
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private PlatformRoutingEnum platformId;

    /**
     * The game mode (Legal values: CLASSIC, ODIN, ARAM, TUTORIAL, ONEFORALL, ASCENSION, FIRSTBLOOD, KINGPORO)
     */
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private GameModeEnum gameMode;

    /**
     * The ID of the map
     */
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private MapEnum mapId;

    /**
     * The game type (Legal values: CUSTOM_GAME, MATCHED_GAME, TUTORIAL_GAME)
     */
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
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
//    private long gameQueueConfigId;
}
