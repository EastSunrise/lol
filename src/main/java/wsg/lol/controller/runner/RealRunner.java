package wsg.lol.controller.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.api.impl.SummonerV4;

/**
 * Runner for update the data from api in real time.
 *
 * @author Kingen
 */
@Service
@Order(value = 3)
public class RealRunner implements ApplicationRunner {

    private SummonerV4 summonerV4;
    private LeagueV4 leagueV4;

    @Override
    public void run(ApplicationArguments args) {
    }

    @Autowired
    public void setSummonerV4(SummonerV4 summonerV4) {
        this.summonerV4 = summonerV4;
    }

    @Autowired
    public void setLeagueV4(LeagueV4 leagueV4) {
        this.leagueV4 = leagueV4;
    }
}
