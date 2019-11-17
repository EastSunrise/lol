package wsg.lol.common.enums.champion;

import wsg.lol.common.enums.intf.EnumList;

/**
 * // TODO: (wangsigen, 2019/11/8)
 *
 * @author wangsigen
 * @date 2019/11/8
 * @since 1.0
 */
public enum ChampionTagEnum implements EnumList {
    Fighter("战士"),
    Tank("坦克"),
    Assassin("刺客"),
    Support("辅助"),
    Mage("法师"),
    Marksman("射手"),
    ;

    private String title;

    ChampionTagEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
