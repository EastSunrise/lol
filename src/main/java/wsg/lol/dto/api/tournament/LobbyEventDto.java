package wsg.lol.dto.api.tournament;

/**
 * @author King
 * @date 2019/2/12
 */
public class LobbyEventDto {

    /**
     * The type of event that was triggered
     */
    private String eventType;

    /**
     * The summonerId that triggered the event (Encrypted)
     */
    private String summonerId;

    /**
     * Timestamp from the event
     */
    private String timestamp;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
