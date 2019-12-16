package wsg.lol.common.enums.share;

import wsg.lol.common.pojo.serialize.JSONSerializable;

/**
 * Enums for types of blocks in recommendations of items.
 *
 * @author Kingen
 */
public enum BlockTypeEnum implements JSONSerializable<String> {
    KingPoroSnax,
    ability_scaling,
    aggressive,
    beginner_Advanced,
    beginner_LegendaryItem,
    beginner_MoreLegendaryItems,
    beginner_MovementSpeed,
    beginner_Starter,
    beginner_advanced,
    beginner_legendary,
    beginner_legendaryitem,
    beginner_morelegendaryitems,
    beginner_movementspeed,
    beginner_movespeed,
    beginner_starter,
    buystarteritems,
    champspecific,
    consumable,
    consumables("消耗品"),
    consumablesjungle,
    defensive("防御型物品"),
    defensivejungle,
    early,
    earlyjungle,
    essential("核心装备"),
    essentialjungle,
    kingporosnax,
    npe1,
    npe2,
    npe3,
    npe4,
    odyjinx1,
    odyjinx2,
    odyjinx3,
    odymalphite1,
    odymalphite2,
    odymalphite3,
    odysona1,
    odysona2,
    odysona3,
    odyyasuo1,
    odyyasuo2,
    odyyasuo3,
    odyziggs1,
    odyziggs2,
    odyziggs3,
    offensive("进攻型物品"),
    offmeta,
    ornnupgrades,
    protective,
    recommended,
    selective,
    siegeDefense,
    siegeOffense,
    siegedefense,
    siegeoffense,
    situational,
    situationaljungle,
    standard,
    standardjungle,
    starting("起始装备"),
    startingjungle,
    support,

    UNKNOWN,
    agressive,
    UNKNOWN2,
    ;

    private String title;

    BlockTypeEnum() {
        this.title = this.name();
    }

    BlockTypeEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String serialize() {
        if (UNKNOWN.equals(this)) {
            return "1)buystarteritems";
        }
        if (UNKNOWN2.equals(this)) {
            return "";
        }
        return name();
    }
}
