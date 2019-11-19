package wsg.lol.common.enums.champion;

/**
 * Enums for tags of the item. todo: title to add.
 *
 * @author Kingen
 */
public enum ItemTagEnum {
    Bilgewater,
    Mana,
    Slow,
    CooldownReduction,
    Armor,
    HealthRegen,
    OnHit,
    SpellVamp,
    GoldPer,
    LifeSteal,
    ManaRegen,
    Damage,
    Aura,
    Tenacity,
    ArmorPenetration,
    Consumable,
    Health,
    NonbootsMovement,
    Lane,
    SpellDamage,
    AttackSpeed,
    Active,
    Vision,
    MagicPenetration,
    CriticalStrike,
    Boots,
    Jungle,
    Stealth,
    Trinket,
    SpellBlock;

    private String title;

    ItemTagEnum() {
        this.title = this.name();
    }

    ItemTagEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
