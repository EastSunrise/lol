package wsg.lol.common.pojo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * Bean for stats of items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemStatsDto extends BaseDto {

    private Integer itemId;

    private Integer FlatHPPoolMod;
    private Integer FlatMPPoolMod;
    private Double FlatHPRegenMod;
    private Integer FlatArmorMod;
    private Integer FlatPhysicalDamageMod;
    private Integer FlatMagicDamageMod;
    private Integer FlatMovementSpeedMod;
    private Double PercentMovementSpeedMod;
    private Double PercentAttackSpeedMod;
    private Double FlatCritChanceMod;
    private Integer FlatSpellBlockMod;
    private Double PercentLifeStealMod;
}
