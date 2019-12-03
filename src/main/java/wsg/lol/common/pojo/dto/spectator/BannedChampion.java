package wsg.lol.common.pojo.dto.spectator;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.TeamIdEnum;
import wsg.lol.common.pojo.serialize.CustomEnumDeserializer;

/**
 * DTO for banned champions of the team.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
class BannedChampion extends BaseDto {

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
    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private TeamIdEnum teamId;
}
