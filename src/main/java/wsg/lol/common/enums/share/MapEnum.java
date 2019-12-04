package wsg.lol.common.enums.share;

import wsg.lol.dao.common.serialize.EqualsToSerializable;
import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enums for maps.
 *
 * @author Kingen
 * @see <a href="http://static.developer.riotgames.com/docs/lol/maps.json">maps.json</a>
 */
public enum MapEnum implements JSONSerializable<String>, EqualsToSerializable<Integer> {
    SummonersRiftOriginalSummer(1, "Summoner's Rift", "Original Summer variant", "原始夏季峡谷"),
    SummonersRiftOriginalAutumn(2, "Summoner's Rift", "Original Autumn variant", "原始秋季峡谷"),
    ProvingGrounds(3, "The Proving Grounds", "Tutorial MapEnum", "练习地图"),
    TwistedTreelineOriginal(4, "Twisted Treeline", "Original Version", "原始扭曲丛林"),
    CrystalScar(8, "The Crystal Scar", "Dominion map", "水晶之痕"),
    TT(10, "Twisted Treeline", "Last TT map", "扭曲丛林"),
    SR(11, "Summoner's Rift", "Current Version", "召唤师峡谷"),
    HA(12, "Howling Abyss", "ARAM map", "嚎哭深渊"),
    ButchersBridge(14, "Butcher's Bridge", "Alternate ARAM map", "屠夫之桥"),
    CosmicRuins(16, "Cosmic Ruins", "Dark Star: Singularity map"),
    CityPark(18, "Valoran City Park", "Star Guardian Invasion map"),
    Substructure43(19, "Substructure 43", "PROJECT: Hunters map"),
    Odyssey(20, "Crash Site", "Odyssey: Extraction map", "奥德赛"),
    NexusBlitz(21, "Nexus Blitz", "Nexus Blitz map", "扭曲闪电战"),
    TeamfightTactics(22, "Teamfight Tactics", "Teamfight Tactics map", "云顶之弈"),

    SL(30, "", ""),
    ProjectSlums(31, "", ""),
    any(32, "", ""),
    gang3(33, "", ""),
    ;

    private int mapId;
    private String mapName;
    private String notes;
    private String title;

    MapEnum(int mapId, String mapName, String notes) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.notes = notes;
        this.title = mapName;
    }

    MapEnum(int mapId, String mapName, String notes, String title) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.notes = notes;
        this.title = title;
    }

    /**
     * Get the enum by the map id.
     */
    public static MapEnum map(int mapId) {
        for (MapEnum value : MapEnum.values()) {
            if (mapId == value.mapId) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown map enum with mapId " + mapId);
    }

    public int getMapId() {
        return mapId;
    }

    public String getMapName() {
        return mapName;
    }

    public String getNotes() {
        return notes;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String serialize() {
        if (this.equals(gang3)) {
            return "3";
        }
        return name();
    }

    @Override
    public boolean equalsToObject(Integer integer) {
        return mapId == integer;
    }
}
