package wsg.lol.common.pojo.dto.match;

import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(name = "currentPlatformId")
    private RegionEnum platformId;

    @JSONField(name = "currentAccountId")
    private String accountId;

    private String summonerName;

    private String summonerId;

    @JSONField(name = "platformId")
    private RegionEnum currentPlatformId;

    @JSONField(name = "accountId")
    private String currentAccountId;

    private String matchHistoryUri;

    private Integer profileIcon;
}
