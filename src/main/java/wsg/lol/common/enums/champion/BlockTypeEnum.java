package wsg.lol.common.enums.champion;

/**
 * Enums for types of blocks in recommendations of items. todo: to extend
 *
 * @author Kingen
 */
public enum BlockTypeEnum {
    beginner_Starter,
    beginner_Advanced,
    beginner_MovementSpeed,
    beginner_LegendaryItem,
    beginner_MoreLegendaryItems,
    starting("起始装备"),
    essential("核心装备"),
    offensive("进攻型物品"),
    defensive("防御型物品"),
    consumables("消耗品"),
    early,
    startingjungle,
    essentialjungle,
    KingPoroSnax,
    siegeOffense,
    siegeDefense,
    earlyjungle,
    offmeta,
    ability_scaling,
    odysona1,
    odysona2,
    odysona3,
    situational,
    beginner_starter,
    beginner_advanced,
    beginner_movementspeed,
    beginner_legendaryitem,
    beginner_morelegendaryitems,
    recommended,
    beginner_movespeed,
    protective,
    aggressive,
    kingporosnax,
    siegeoffense,
    siegedefense,
    defensivejungle,
    situationaljungle,
    standard,
    support,
    selective,
    beginner_legendary,
    ornnupgrades,
    npe1,
    npe2,
    npe3,
    npe4,
    odyyasuo1,
    odyyasuo2,
    odyyasuo3,
    odyjinx1,
    odyjinx2,
    odyjinx3,
    champspecific,
    buystarteritems,
    odyziggs1,
    odyziggs2,
    odyziggs3,
    consumablesjungle,
    standardjungle,
    odymalphite1,
    odymalphite2,
    odymalphite3,
    consumable,
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
}
