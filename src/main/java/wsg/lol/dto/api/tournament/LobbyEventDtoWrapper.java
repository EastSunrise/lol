package wsg.lol.dto.api.tournament;

import java.util.List;

/**
 * @author King
 * @date 2019/2/12
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
