package wsg.lol.common.pojo.domain.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for stats of items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "i_stats")
public class ItemStatsDo extends BaseDo {

    @Id
    private Integer itemId;

    @Column(name = "flat_hp_pool_mod")
    private Integer flatHPPoolMod;

    @Column(name = "flat_mp_pool_mod")
    private Integer flatMPPoolMod;

    @Column(name = "flat_hp_regen_mod")
    private Double flatHPRegenMod;

    @Column
    private Integer flatArmorMod;

    @Column
    private Integer flatPhysicalDamageMod;

    @Column
    private Integer flatMagicDamageMod;

    @Column
    private Integer flatMovementSpeedMod;

    @Column
    private Double percentMovementSpeedMod;

    @Column
    private Double percentAttackSpeedMod;

    @Column
    private Double flatCritChanceMod;

    @Column
    private Integer flatSpellBlockMod;

    @Column
    private Double percentLifeStealMod;
}