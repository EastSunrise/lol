package wsg.lol.common.enums.share;

import wsg.lol.common.pojo.serialize.JSONSerializable;

/**
 * Enums for tags of the item.
 *
 * @author Kingen
 */
public enum ItemTagEnum implements JSONSerializable<String> {
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

    @Override
    public String serialize() {
        return name();
    }
}
