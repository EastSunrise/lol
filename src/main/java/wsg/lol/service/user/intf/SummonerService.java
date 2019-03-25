package wsg.lol.service.user.intf;

import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.dto.query.GetSummonerDto;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-07 15:35
 */
public interface SummonerService {

    SummonerDmo getSummonerById(String id);

    BaseResult querySummonerOverview(GetSummonerDto getSummonerDto);

}
