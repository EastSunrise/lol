package wsg.lol.pojo.event;

import org.springframework.context.ApplicationEvent;

/**
 * wsg
 *
 * @author wangsigen
 */
public class SummonerEvent extends ApplicationEvent {

    private String summonerName;

    public SummonerEvent(String summonerName) {
        super(new Object());
        this.summonerName = summonerName;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }
}
