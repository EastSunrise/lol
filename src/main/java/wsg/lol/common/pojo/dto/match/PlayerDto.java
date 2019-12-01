package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * DTO for information of players.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
class PlayerDto extends BaseDto {

//    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
//    private PlatformRoutingEnum platformId;

    private String accountId;

    private String summonerName;

    private String summonerId;

//    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
//    private PlatformRoutingEnum currentPlatformId;

    private String currentAccountId;

    private String matchHistoryUri;

    private Integer profileIcon;
}
