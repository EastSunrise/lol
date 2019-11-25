package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.route.PlatformRoutingEnum;

/**
 * Bean for information of players.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerDto extends BaseDto {

    private PlatformRoutingEnum currentPlatformId;
    private String summonerName;
    private String matchHistoryUri;

    /**
     * Original platformId.
     */
    private PlatformRoutingEnum platformId;

    /**
     * Player's current accountId (Encrypted)
     */
    private String currentAccountId;
    private Integer profileIcon;

    /**
     * Player's summonerId (Encrypted)
     */
    private String summonerId;

    /**
     * Player's original accountId (Encrypted)
     */
    private String accountId;
}
