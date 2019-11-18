package wsg.lol.common.pojo.dto.match;

import java.util.List;
import java.util.Map;

/**
 * // TODO: (Kingen, 2019/11/18)
 * @author EastSunrise
 */
public class MatchFrameDto {

    private long timestamp;
    private Map<String, MatchParticipantFrameDto> participantFrames;
    private List<MatchEventDto> events;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, MatchParticipantFrameDto> getParticipantFrames() {
        return participantFrames;
    }

    public void setParticipantFrames(Map<String, MatchParticipantFrameDto> participantFrames) {
        this.participantFrames = participantFrames;
    }

    public List<MatchEventDto> getEvents() {
        return events;
    }

    public void setEvents(List<MatchEventDto> events) {
        this.events = events;
    }

}
