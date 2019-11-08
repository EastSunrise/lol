package wsg.lol.common.enums.game;

/**
 * 对局类型枚举
 *
 * @author EastSunrise
 * @date 2019/11/6
 * @see <a href="http://static.developer.riotgames.com/docs/lol/queues.json">queues.json</a>
 * @since 1.0
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
