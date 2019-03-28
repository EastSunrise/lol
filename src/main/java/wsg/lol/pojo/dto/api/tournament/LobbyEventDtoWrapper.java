package wsg.lol.pojo.dto.api.tournament;

import java.util.List;

/**
 * @author King
 */
public class LobbyEventDtoWrapper {

    private List<LobbyEventDto> eventList;

    public List<LobbyEventDto> getEventList() {
        return eventList;
    }

    public void setEventList(List<LobbyEventDto> eventList) {
        this.eventList = eventList;
    }
}
