package wsg.lol.common.pojo.dto.item;

import wsg.lol.common.pojo.base.BaseDto;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class ItemStatsDto extends BaseDto {

    private Integer itemId;

    private int FlatHPPoolMod;
    private int rFlatHPModPerLevel;
    private int FlatMPPoolMod;
    private int rFlatMPModPerLevel;
    private int PercentHPPoolMod;
    private int PercentMPPoolMod;
    private int FlatHPRegenMod;
    private int rFlatHPRegenModPerLevel;
    private int PercentHPRegenMod;
    private int FlatMPRegenMod;
    private int rFlatMPRegenModPerLevel;
    private int PercentMPRegenMod;
    private int FlatArmorMod;
    private int rFlatArmorModPerLevel;
    private int PercentArmorMod;
    private int rFlatArmorPenetrationMod;
    private int rFlatArmorPenetrationModPerLevel;
    private int rPercentArmorPenetrationMod;
    private int rPercentArmorPenetrationModPerLevel;
    private int FlatPhysicalDamageMod;
    private int rFlatPhysicalDamageModPerLevel;
    private int PercentPhysicalDamageMod;
    private int FlatMagicDamageMod;
    private int rFlatMagicDamageModPerLevel;
    private int PercentMagicDamageMod;
    private int FlatMovementSpeedMod;
    private int rFlatMovementSpeedModPerLevel;
    private int PercentMovementSpeedMod;
    private int rPercentMovementSpeedModPerLevel;
    private int FlatAttackSpeedMod;
    private int PercentAttackSpeedMod;
    private int rPercentAttackSpeedModPerLevel;
    private int rFlatDodgeMod;
    private int rFlatDodgeModPerLevel;
    private int PercentDodgeMod;
    private int FlatCritChanceMod;
    private int rFlatCritChanceModPerLevel;
    private int PercentCritChanceMod;
    private int FlatCritDamageMod;
    private int rFlatCritDamageModPerLevel;
    private int PercentCritDamageMod;
    private int FlatBlockMod;
    private int PercentBlockMod;
    private int FlatSpellBlockMod;
    private int rFlatSpellBlockModPerLevel;
    private int PercentSpellBlockMod;
    private int FlatEXPBonus;
    private int PercentEXPBonus;
    private int rPercentCooldownMod;
    private int rPercentCooldownModPerLevel;
    private int rFlatTimeDeadMod;
    private int rFlatTimeDeadModPerLevel;
    private int rPercentTimeDeadMod;
    private int rPercentTimeDeadModPerLevel;
    private int rFlatGoldPer10Mod;
    private int rFlatMagicPenetrationMod;
    private int rFlatMagicPenetrationModPerLevel;
    private int rPercentMagicPenetrationMod;
    private int rPercentMagicPenetrationModPerLevel;
    private int FlatEnergyRegenMod;
    private int rFlatEnergyRegenModPerLevel;
    private int FlatEnergyPoolMod;
    private int rFlatEnergyModPerLevel;
    private int PercentLifeStealMod;
    private int PercentSpellVampMod;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public int getFlatHPPoolMod() {
        return FlatHPPoolMod;
    }

    public void setFlatHPPoolMod(int FlatHPPoolMod) {
        this.FlatHPPoolMod = FlatHPPoolMod;
    }

    public int getRFlatHPModPerLevel() {
        return rFlatHPModPerLevel;
    }

    public void setRFlatHPModPerLevel(int rFlatHPModPerLevel) {
        this.rFlatHPModPerLevel = rFlatHPModPerLevel;
    }

    public int getFlatMPPoolMod() {
        return FlatMPPoolMod;
    }

    public void setFlatMPPoolMod(int FlatMPPoolMod) {
        this.FlatMPPoolMod = FlatMPPoolMod;
    }

    public int getRFlatMPModPerLevel() {
        return rFlatMPModPerLevel;
    }

    public void setRFlatMPModPerLevel(int rFlatMPModPerLevel) {
        this.rFlatMPModPerLevel = rFlatMPModPerLevel;
    }

    public int getPercentHPPoolMod() {
        return PercentHPPoolMod;
    }

    public void setPercentHPPoolMod(int PercentHPPoolMod) {
        this.PercentHPPoolMod = PercentHPPoolMod;
    }

    public int getPercentMPPoolMod() {
        return PercentMPPoolMod;
    }

    public void setPercentMPPoolMod(int PercentMPPoolMod) {
        this.PercentMPPoolMod = PercentMPPoolMod;
    }

    public int getFlatHPRegenMod() {
        return FlatHPRegenMod;
    }

    public void setFlatHPRegenMod(int FlatHPRegenMod) {
        this.FlatHPRegenMod = FlatHPRegenMod;
    }

    public int getRFlatHPRegenModPerLevel() {
        return rFlatHPRegenModPerLevel;
    }

    public void setRFlatHPRegenModPerLevel(int rFlatHPRegenModPerLevel) {
        this.rFlatHPRegenModPerLevel = rFlatHPRegenModPerLevel;
    }

    public int getPercentHPRegenMod() {
        return PercentHPRegenMod;
    }

    public void setPercentHPRegenMod(int PercentHPRegenMod) {
        this.PercentHPRegenMod = PercentHPRegenMod;
    }

    public int getFlatMPRegenMod() {
        return FlatMPRegenMod;
    }

    public void setFlatMPRegenMod(int FlatMPRegenMod) {
        this.FlatMPRegenMod = FlatMPRegenMod;
    }

    public int getRFlatMPRegenModPerLevel() {
        return rFlatMPRegenModPerLevel;
    }

    public void setRFlatMPRegenModPerLevel(int rFlatMPRegenModPerLevel) {
        this.rFlatMPRegenModPerLevel = rFlatMPRegenModPerLevel;
    }

    public int getPercentMPRegenMod() {
        return PercentMPRegenMod;
    }

    public void setPercentMPRegenMod(int PercentMPRegenMod) {
        this.PercentMPRegenMod = PercentMPRegenMod;
    }

    public int getFlatArmorMod() {
        return FlatArmorMod;
    }

    public void setFlatArmorMod(int FlatArmorMod) {
        this.FlatArmorMod = FlatArmorMod;
    }

    public int getRFlatArmorModPerLevel() {
        return rFlatArmorModPerLevel;
    }

    public void setRFlatArmorModPerLevel(int rFlatArmorModPerLevel) {
        this.rFlatArmorModPerLevel = rFlatArmorModPerLevel;
    }

    public int getPercentArmorMod() {
        return PercentArmorMod;
    }

    public void setPercentArmorMod(int PercentArmorMod) {
        this.PercentArmorMod = PercentArmorMod;
    }

    public int getRFlatArmorPenetrationMod() {
        return rFlatArmorPenetrationMod;
    }

    public void setRFlatArmorPenetrationMod(int rFlatArmorPenetrationMod) {
        this.rFlatArmorPenetrationMod = rFlatArmorPenetrationMod;
    }

    public int getRFlatArmorPenetrationModPerLevel() {
        return rFlatArmorPenetrationModPerLevel;
    }

    public void setRFlatArmorPenetrationModPerLevel(int rFlatArmorPenetrationModPerLevel) {
        this.rFlatArmorPenetrationModPerLevel = rFlatArmorPenetrationModPerLevel;
    }

    public int getRPercentArmorPenetrationMod() {
        return rPercentArmorPenetrationMod;
    }

    public void setRPercentArmorPenetrationMod(int rPercentArmorPenetrationMod) {
        this.rPercentArmorPenetrationMod = rPercentArmorPenetrationMod;
    }

    public int getRPercentArmorPenetrationModPerLevel() {
        return rPercentArmorPenetrationModPerLevel;
    }

    public void setRPercentArmorPenetrationModPerLevel(int rPercentArmorPenetrationModPerLevel) {
        this.rPercentArmorPenetrationModPerLevel = rPercentArmorPenetrationModPerLevel;
    }

    public int getFlatPhysicalDamageMod() {
        return FlatPhysicalDamageMod;
    }

    public void setFlatPhysicalDamageMod(int FlatPhysicalDamageMod) {
        this.FlatPhysicalDamageMod = FlatPhysicalDamageMod;
    }

    public int getRFlatPhysicalDamageModPerLevel() {
        return rFlatPhysicalDamageModPerLevel;
    }

    public void setRFlatPhysicalDamageModPerLevel(int rFlatPhysicalDamageModPerLevel) {
        this.rFlatPhysicalDamageModPerLevel = rFlatPhysicalDamageModPerLevel;
    }

    public int getPercentPhysicalDamageMod() {
        return PercentPhysicalDamageMod;
    }

    public void setPercentPhysicalDamageMod(int PercentPhysicalDamageMod) {
        this.PercentPhysicalDamageMod = PercentPhysicalDamageMod;
    }

    public int getFlatMagicDamageMod() {
        return FlatMagicDamageMod;
    }

    public void setFlatMagicDamageMod(int FlatMagicDamageMod) {
        this.FlatMagicDamageMod = FlatMagicDamageMod;
    }

    public int getRFlatMagicDamageModPerLevel() {
        return rFlatMagicDamageModPerLevel;
    }

    public void setRFlatMagicDamageModPerLevel(int rFlatMagicDamageModPerLevel) {
        this.rFlatMagicDamageModPerLevel = rFlatMagicDamageModPerLevel;
    }

    public int getPercentMagicDamageMod() {
        return PercentMagicDamageMod;
    }

    public void setPercentMagicDamageMod(int PercentMagicDamageMod) {
        this.PercentMagicDamageMod = PercentMagicDamageMod;
    }

    public int getFlatMovementSpeedMod() {
        return FlatMovementSpeedMod;
    }

    public void setFlatMovementSpeedMod(int FlatMovementSpeedMod) {
        this.FlatMovementSpeedMod = FlatMovementSpeedMod;
    }

    public int getRFlatMovementSpeedModPerLevel() {
        return rFlatMovementSpeedModPerLevel;
    }

    public void setRFlatMovementSpeedModPerLevel(int rFlatMovementSpeedModPerLevel) {
        this.rFlatMovementSpeedModPerLevel = rFlatMovementSpeedModPerLevel;
    }

    public int getPercentMovementSpeedMod() {
        return PercentMovementSpeedMod;
    }

    public void setPercentMovementSpeedMod(int PercentMovementSpeedMod) {
        this.PercentMovementSpeedMod = PercentMovementSpeedMod;
    }

    public int getRPercentMovementSpeedModPerLevel() {
        return rPercentMovementSpeedModPerLevel;
    }

    public void setRPercentMovementSpeedModPerLevel(int rPercentMovementSpeedModPerLevel) {
        this.rPercentMovementSpeedModPerLevel = rPercentMovementSpeedModPerLevel;
    }

    public int getFlatAttackSpeedMod() {
        return FlatAttackSpeedMod;
    }

    public void setFlatAttackSpeedMod(int FlatAttackSpeedMod) {
        this.FlatAttackSpeedMod = FlatAttackSpeedMod;
    }

    public int getPercentAttackSpeedMod() {
        return PercentAttackSpeedMod;
    }

    public void setPercentAttackSpeedMod(int PercentAttackSpeedMod) {
        this.PercentAttackSpeedMod = PercentAttackSpeedMod;
    }

    public int getRPercentAttackSpeedModPerLevel() {
        return rPercentAttackSpeedModPerLevel;
    }

    public void setRPercentAttackSpeedModPerLevel(int rPercentAttackSpeedModPerLevel) {
        this.rPercentAttackSpeedModPerLevel = rPercentAttackSpeedModPerLevel;
    }

    public int getRFlatDodgeMod() {
        return rFlatDodgeMod;
    }

    public void setRFlatDodgeMod(int rFlatDodgeMod) {
        this.rFlatDodgeMod = rFlatDodgeMod;
    }

    public int getRFlatDodgeModPerLevel() {
        return rFlatDodgeModPerLevel;
    }

    public void setRFlatDodgeModPerLevel(int rFlatDodgeModPerLevel) {
        this.rFlatDodgeModPerLevel = rFlatDodgeModPerLevel;
    }

    public int getPercentDodgeMod() {
        return PercentDodgeMod;
    }

    public void setPercentDodgeMod(int PercentDodgeMod) {
        this.PercentDodgeMod = PercentDodgeMod;
    }

    public int getFlatCritChanceMod() {
        return FlatCritChanceMod;
    }

    public void setFlatCritChanceMod(int FlatCritChanceMod) {
        this.FlatCritChanceMod = FlatCritChanceMod;
    }

    public int getRFlatCritChanceModPerLevel() {
        return rFlatCritChanceModPerLevel;
    }

    public void setRFlatCritChanceModPerLevel(int rFlatCritChanceModPerLevel) {
        this.rFlatCritChanceModPerLevel = rFlatCritChanceModPerLevel;
    }

    public int getPercentCritChanceMod() {
        return PercentCritChanceMod;
    }

    public void setPercentCritChanceMod(int PercentCritChanceMod) {
        this.PercentCritChanceMod = PercentCritChanceMod;
    }

    public int getFlatCritDamageMod() {
        return FlatCritDamageMod;
    }

    public void setFlatCritDamageMod(int FlatCritDamageMod) {
        this.FlatCritDamageMod = FlatCritDamageMod;
    }

    public int getRFlatCritDamageModPerLevel() {
        return rFlatCritDamageModPerLevel;
    }

    public void setRFlatCritDamageModPerLevel(int rFlatCritDamageModPerLevel) {
        this.rFlatCritDamageModPerLevel = rFlatCritDamageModPerLevel;
    }

    public int getPercentCritDamageMod() {
        return PercentCritDamageMod;
    }

    public void setPercentCritDamageMod(int PercentCritDamageMod) {
        this.PercentCritDamageMod = PercentCritDamageMod;
    }

    public int getFlatBlockMod() {
        return FlatBlockMod;
    }

    public void setFlatBlockMod(int FlatBlockMod) {
        this.FlatBlockMod = FlatBlockMod;
    }

    public int getPercentBlockMod() {
        return PercentBlockMod;
    }

    public void setPercentBlockMod(int PercentBlockMod) {
        this.PercentBlockMod = PercentBlockMod;
    }

    public int getFlatSpellBlockMod() {
        return FlatSpellBlockMod;
    }

    public void setFlatSpellBlockMod(int FlatSpellBlockMod) {
        this.FlatSpellBlockMod = FlatSpellBlockMod;
    }

    public int getRFlatSpellBlockModPerLevel() {
        return rFlatSpellBlockModPerLevel;
    }

    public void setRFlatSpellBlockModPerLevel(int rFlatSpellBlockModPerLevel) {
        this.rFlatSpellBlockModPerLevel = rFlatSpellBlockModPerLevel;
    }

    public int getPercentSpellBlockMod() {
        return PercentSpellBlockMod;
    }

    public void setPercentSpellBlockMod(int PercentSpellBlockMod) {
        this.PercentSpellBlockMod = PercentSpellBlockMod;
    }

    public int getFlatEXPBonus() {
        return FlatEXPBonus;
    }

    public void setFlatEXPBonus(int FlatEXPBonus) {
        this.FlatEXPBonus = FlatEXPBonus;
    }

    public int getPercentEXPBonus() {
        return PercentEXPBonus;
    }

    public void setPercentEXPBonus(int PercentEXPBonus) {
        this.PercentEXPBonus = PercentEXPBonus;
    }

    public int getRPercentCooldownMod() {
        return rPercentCooldownMod;
    }

    public void setRPercentCooldownMod(int rPercentCooldownMod) {
        this.rPercentCooldownMod = rPercentCooldownMod;
    }

    public int getRPercentCooldownModPerLevel() {
        return rPercentCooldownModPerLevel;
    }

    public void setRPercentCooldownModPerLevel(int rPercentCooldownModPerLevel) {
        this.rPercentCooldownModPerLevel = rPercentCooldownModPerLevel;
    }

    public int getRFlatTimeDeadMod() {
        return rFlatTimeDeadMod;
    }

    public void setRFlatTimeDeadMod(int rFlatTimeDeadMod) {
        this.rFlatTimeDeadMod = rFlatTimeDeadMod;
    }

    public int getRFlatTimeDeadModPerLevel() {
        return rFlatTimeDeadModPerLevel;
    }

    public void setRFlatTimeDeadModPerLevel(int rFlatTimeDeadModPerLevel) {
        this.rFlatTimeDeadModPerLevel = rFlatTimeDeadModPerLevel;
    }

    public int getRPercentTimeDeadMod() {
        return rPercentTimeDeadMod;
    }

    public void setRPercentTimeDeadMod(int rPercentTimeDeadMod) {
        this.rPercentTimeDeadMod = rPercentTimeDeadMod;
    }

    public int getRPercentTimeDeadModPerLevel() {
        return rPercentTimeDeadModPerLevel;
    }

    public void setRPercentTimeDeadModPerLevel(int rPercentTimeDeadModPerLevel) {
        this.rPercentTimeDeadModPerLevel = rPercentTimeDeadModPerLevel;
    }

    public int getRFlatGoldPer10Mod() {
        return rFlatGoldPer10Mod;
    }

    public void setRFlatGoldPer10Mod(int rFlatGoldPer10Mod) {
        this.rFlatGoldPer10Mod = rFlatGoldPer10Mod;
    }

    public int getRFlatMagicPenetrationMod() {
        return rFlatMagicPenetrationMod;
    }

    public void setRFlatMagicPenetrationMod(int rFlatMagicPenetrationMod) {
        this.rFlatMagicPenetrationMod = rFlatMagicPenetrationMod;
    }

    public int getRFlatMagicPenetrationModPerLevel() {
        return rFlatMagicPenetrationModPerLevel;
    }

    public void setRFlatMagicPenetrationModPerLevel(int rFlatMagicPenetrationModPerLevel) {
        this.rFlatMagicPenetrationModPerLevel = rFlatMagicPenetrationModPerLevel;
    }

    public int getRPercentMagicPenetrationMod() {
        return rPercentMagicPenetrationMod;
    }

    public void setRPercentMagicPenetrationMod(int rPercentMagicPenetrationMod) {
        this.rPercentMagicPenetrationMod = rPercentMagicPenetrationMod;
    }

    public int getRPercentMagicPenetrationModPerLevel() {
        return rPercentMagicPenetrationModPerLevel;
    }

    public void setRPercentMagicPenetrationModPerLevel(int rPercentMagicPenetrationModPerLevel) {
        this.rPercentMagicPenetrationModPerLevel = rPercentMagicPenetrationModPerLevel;
    }

    public int getFlatEnergyRegenMod() {
        return FlatEnergyRegenMod;
    }

    public void setFlatEnergyRegenMod(int FlatEnergyRegenMod) {
        this.FlatEnergyRegenMod = FlatEnergyRegenMod;
    }

    public int getRFlatEnergyRegenModPerLevel() {
        return rFlatEnergyRegenModPerLevel;
    }

    public void setRFlatEnergyRegenModPerLevel(int rFlatEnergyRegenModPerLevel) {
        this.rFlatEnergyRegenModPerLevel = rFlatEnergyRegenModPerLevel;
    }

    public int getFlatEnergyPoolMod() {
        return FlatEnergyPoolMod;
    }

    public void setFlatEnergyPoolMod(int FlatEnergyPoolMod) {
        this.FlatEnergyPoolMod = FlatEnergyPoolMod;
    }

    public int getRFlatEnergyModPerLevel() {
        return rFlatEnergyModPerLevel;
    }

    public void setRFlatEnergyModPerLevel(int rFlatEnergyModPerLevel) {
        this.rFlatEnergyModPerLevel = rFlatEnergyModPerLevel;
    }

    public int getPercentLifeStealMod() {
        return PercentLifeStealMod;
    }

    public void setPercentLifeStealMod(int PercentLifeStealMod) {
        this.PercentLifeStealMod = PercentLifeStealMod;
    }

    public int getPercentSpellVampMod() {
        return PercentSpellVampMod;
    }

    public void setPercentSpellVampMod(int PercentSpellVampMod) {
        this.PercentSpellVampMod = PercentSpellVampMod;
    }
}
