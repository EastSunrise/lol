package wsg.lol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.dao.mapper.SummonerMapper;
import wsg.lol.dmo.summoner.SummonerDmo;
import wsg.lol.service.intf.SummonerService;

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

    @Override
    public SummonerDmo getSummonerById(String id) {
        return summonerMapper.selectByPrimaryKey(id);
    }
}
