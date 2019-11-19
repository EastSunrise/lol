package wsg.lol.service.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.dao.mybatis.mapper.SummonerMapper;
import wsg.lol.service.main.intf.SummonerService;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Service("summonerService")
public class SummonerServiceImpl implements SummonerService {

    private SummonerMapper summonerMapper;

    @Override
    public SummonerDmo getSummonerById(String id) {
//        return summonerMapper.selectByPrimaryKey(id);
        return null;
    }

    @Autowired
    public void setSummonerMapper(SummonerMapper summonerMapper) {
        this.summonerMapper = summonerMapper;
    }
}
