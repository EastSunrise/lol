package wsg.lol.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import wsg.lol.dao.mybatis.mapper.LeaguePositionMapper;
import wsg.lol.dao.mybatis.mapper.SummonerMapper;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.dto.query.GetSummonerDto;
import wsg.lol.service.user.intf.SummonerService;

/**
 * wsg
 *
 * @author wangsigen
 */
@Service("summonerService")
public class SummonerServiceImpl implements SummonerService {

    private ApplicationEventPublisher eventPublisher;

    private SummonerMapper summonerMapper;

    private LeaguePositionMapper leaguePositionMapper;

    @Override
    public SummonerDmo getSummonerById(String id) {
        return summonerMapper.selectByPrimaryKey(id);
    }

    @Override
    public BaseResult querySummonerOverview(GetSummonerDto getSummonerDto) {
        return BaseResult.success();
    }

    @Autowired
    public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Autowired
    public void setSummonerMapper(SummonerMapper summonerMapper) {
        this.summonerMapper = summonerMapper;
    }

    @Autowired
    public void setLeaguePositionMapper(LeaguePositionMapper leaguePositionMapper) {
        this.leaguePositionMapper = leaguePositionMapper;
    }
}
