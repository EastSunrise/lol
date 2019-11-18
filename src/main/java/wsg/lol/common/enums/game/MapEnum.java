package wsg.lol.common.enums.game;

/**
 * Enums for maps.
 *
 * @author Kingen
 * @see <a href="http://static.developer.riotgames.com/docs/lol/maps.json">maps.json</a>
 */
public enum MapEnum {
    SummonersRiftOriginalSummer(1, "Summoner's Rift", "Original Summer variant"),
    SummonersRiftOriginalAutumn(2, "Summoner's Rift", "Original Autumn variant"),
    TheProvingGrounds(3, "The Proving Grounds", "Tutorial Map"),
    TwistedTreelineOriginal(4, "Twisted Treeline", "Original Version"),
    TheCrystalScar(8, "The Crystal Scar", "Dominion map"),
    TwistedTreeline(10, "Twisted Treeline", "Last TT map"),
    SummonerRift(11, "Summoner's Rift", "Current Version"),
    HowlingAbyss(12, "Howling Abyss", "ARAM map"),
    ButchersBridge(14, "Butcher's Bridge", "Alternate ARAM map"),
    CosmicRuins(16, "Cosmic Ruins", "Dark Star: Singularity map"),
    ValoranCityPark(18, "Valoran City Park", "Star Guardian Invasion map"),
    Substructure43(19, "Substructure 43", "PROJECT: Hunters map"),
    CrashSite(20, "Crash Site", "Odyssey: Extraction map"),
    NexusBlitz(21, "Nexus Blitz", "Nexus Blitz map"),
    TeamfightTactics(22, "Teamfight Tactics", "云顶之弈"),
    ;

    private int mapId;
    private String mapName;
    private String notes;

    MapEnum(int mapId, String mapName, String notes) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.notes = notes;
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
}
