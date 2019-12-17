package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.system.RegionEnum;

/**
 * DTO for information of players.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerDto extends BaseDto {

    private RegionEnum platformId;

    private String accountId;

    private String summonerName;

    private String summonerId;

    private RegionEnum currentPlatformId;

    private String currentAccountId;

    private String matchHistoryUri;

    private Integer profileIcon;
}
