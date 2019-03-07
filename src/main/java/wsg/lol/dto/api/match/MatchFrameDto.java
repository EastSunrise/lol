package wsg.lol.dto.api.match;

import java.util.List;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/12
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
