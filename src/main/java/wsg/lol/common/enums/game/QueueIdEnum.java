package wsg.lol.common.enums.game;

/**
 * Enums for the type of the queue.
 *
 * @author Kingen
 * @see <a href="http://static.developer.riotgames.com/docs/lol/queues.json">queues.json</a>
 */
public enum QueueIdEnum {
    BlindPickGames5v5(2, MapEnum.SummonerRift, "5v5 Blind Pick games", "Deprecated in patch 7.19 in favor of queueId 430");

    private int queueId;
    private MapEnum map;
    private String description;
    private String notes;

    QueueIdEnum(int queueId, MapEnum map, String description, String notes) {
        this.queueId = queueId;
        this.map = map;
        this.description = description;
        this.notes = notes;
    }

    public int getQueueId() {
        return queueId;
    }

    public MapEnum getMap() {
        return map;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }
}
