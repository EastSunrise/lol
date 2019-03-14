package wsg.lol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.base.BaseResult;
import wsg.lol.dao.mapper.PositionMapper;
import wsg.lol.dao.mapper.SummonerMapper;
import wsg.lol.data.api.LeagueV4;
import wsg.lol.data.api.SummonerV4;
import wsg.lol.dmo.league.PositionDmo;
import wsg.lol.dmo.summoner.SummonerDmo;
import wsg.lol.dto.query.GetSummonerDto;
import wsg.lol.dto.result.SummonerResult;
import wsg.lol.service.intf.SummonerService;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-07 15:36
 */
@Service("summonerService")
public class SummonerServiceImpl implements SummonerService {

    @Autowired
    private SummonerMapper summonerMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public SummonerDmo getSummonerById(String id) {
        return summonerMapper.selectByPrimaryKey(id);
    }

    @Override
    public SummonerResult querySummonerOverview(GetSummonerDto getSummonerDto) {
        SummonerDmo summonerDmo = summonerMapper.selectByCond(getSummonerDto);
        if (summonerDmo == null) {
            summonerDmo = SummonerV4.getSummoner(getSummonerDto);
            if (1 != summonerMapper.insertSummoner(summonerDmo)) {
                return (SummonerResult) BaseResult.fail("Fail to add summoner.");
            }
        }
        if (!updateSummonerData(summonerDmo).isSuccess()) {
            return (SummonerResult) BaseResult.fail("Fail to update data of summoner.");
        }

        assert summonerDmo != null;
        PositionDmo positionDmo = positionMapper.selectBySummonerId(summonerDmo.getId());

        SummonerResult summonerResult = new SummonerResult();
        summonerResult.setSummonerDmo(summonerDmo);
        summonerResult.setPositionDmo(positionDmo);
        return summonerResult;
    }

    @Override
    public BaseResult updateSummonerData(SummonerDmo summonerDmo) {
        List<PositionDmo> positionDmoList = LeagueV4.getLeaguePositionsBySummonerId(summonerDmo.getId());
        positionMapper.batchInsertPosition(positionDmoList);
        for (PositionDmo positionDmo : positionDmoList) {

        }

        return BaseResult.success();
    }
}
