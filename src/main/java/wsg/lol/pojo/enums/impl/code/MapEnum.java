package wsg.lol.pojo.enums.impl.code;

import wsg.lol.pojo.enums.intf.BaseEnum;

/**
 * wsg
 *
 * @author wangsigen
 */
public enum MapEnum implements BaseEnum<Integer> {
    OriginalSummerSummonersRift(1),
    OriginalAutumnSummonersRift(2),
    TheProvingGrounds(3),
    OriginalTwistedTreeline(4),
    TheCrystalScar(8),
    TwistedTreeline(10),
    SummonersRift(11),
    HowlingAbyss(12),
    ButchersBridge(14),
    CosmicRuins(16),
    ValoranCityPark(18),
    Substructure43(19),
    CrashSite(20),
    NexusBlitz(21),
    ;

    private int value;

    MapEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
