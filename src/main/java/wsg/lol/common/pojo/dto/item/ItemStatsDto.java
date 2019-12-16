package wsg.lol.common.pojo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * DTO for stats of items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
class ItemStatsDto extends BaseDto {

    private Integer flatHPPoolMod;

    private Integer flatMPPoolMod;

    private Double flatHPRegenMod;

    private Integer flatArmorMod;

    private Integer flatPhysicalDamageMod;

    private Integer flatMagicDamageMod;

    private Integer flatMovementSpeedMod;

    private Double percentMovementSpeedMod;

    private Double percentAttackSpeedMod;

    private Double flatCritChanceMod;

    private Integer flatSpellBlockMod;

    private Double percentLifeStealMod;
}
