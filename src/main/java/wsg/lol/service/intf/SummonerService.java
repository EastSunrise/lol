package wsg.lol.service.intf;

import wsg.lol.dmo.summoner.SummonerDmo;
import wsg.lol.dto.query.GetSummonerDto;
import wsg.lol.dto.result.SummonerResult;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-07 15:35
 */
public interface SummonerService {

    SummonerDmo getSummonerById(String id);

    void test();

    SummonerResult querySummonerOverview(GetSummonerDto getSummonerDto);
}
