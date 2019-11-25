package wsg.lol.common.pojo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for stats of items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "i_stats")
public class ItemStatsDto extends BaseDto {

    @Id
    private Integer itemId;

    @Column(name = "flat_hp_pool_mod")
    private Integer FlatHPPoolMod;
    @Column(name = "flat_mp_pool_mod")
    private Integer FlatMPPoolMod;
    @Column(name = "flat_hp_regen_mod")
    private Double FlatHPRegenMod;
    @Column
    private Integer FlatArmorMod;
    @Column
    private Integer FlatPhysicalDamageMod;
    @Column
    private Integer FlatMagicDamageMod;
    @Column
    private Integer FlatMovementSpeedMod;
    @Column
    private Double PercentMovementSpeedMod;
    @Column
    private Double PercentAttackSpeedMod;
    @Column
    private Double FlatCritChanceMod;
    @Column
    private Integer FlatSpellBlockMod;
    @Column
    private Double PercentLifeStealMod;
}
