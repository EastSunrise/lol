package wsg.lol.common.enums.game;

/**
 * Enums for maps. TODO: enum to extend and title to add.
 *
 * @author Kingen
 * @see <a href="http://static.developer.riotgames.com/docs/lol/maps.json">maps.json</a>
 */
public enum MapEnum {
    SummonersRiftOriginalSummer(1, "Summoner's Rift", "Original Summer variant", "原始夏季峡谷"),
    SummonersRiftOriginalAutumn(2, "Summoner's Rift", "Original Autumn variant", "原始秋季峡谷"),
    ProvingGrounds(3, "The Proving Grounds", "Tutorial Map", "练习地图"),
    TwistedTreelineOriginal(4, "Twisted Treeline", "Original Version", "原始扭曲丛林"),
    CrystalScar(8, "The Crystal Scar", "Dominion map", "水晶之痕"),
    TT(10, "Twisted Treeline", "Last TT map", "扭曲丛林"),
    SR(11, "Summoner's Rift", "Current Version", "召唤师峡谷"),
    HA(12, "Howling Abyss", "ARAM map", "嚎哭深渊"),
    ButchersBridge(14, "Butcher's Bridge", "Alternate ARAM map", "屠夫之桥"),
    CosmicRuins(16, "Cosmic Ruins", "Dark Star: Singularity map"),
    ValoranCityPark(18, "Valoran City Park", "Star Guardian Invasion map"),
    Substructure43(19, "Substructure 43", "PROJECT: Hunters map"),
    Odyssey(20, "Crash Site", "Odyssey: Extraction map", "奥德赛"),
    NexusBlitz(21, "Nexus Blitz", "Nexus Blitz map", "扭曲闪电战"),
    TeamfightTactics(22, "Teamfight Tactics", "Teamfight Tactics map", "云顶之弈"),
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
}
