package wsg.lol.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.dao.mybatis.mapper.PositionMapper;
import wsg.lol.dao.mybatis.mapper.SummonerMapper;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.dmo.league.PositionDmo;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.dto.query.GetSummonerDto;
import wsg.lol.pojo.dto.result.SummonerResult;
import wsg.lol.pojo.event.SummonerEvent;
import wsg.lol.service.user.intf.SummonerService;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-07 15:36
 */
@Service("summonerService")
public class SummonerServiceImpl implements SummonerService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private SummonerMapper summonerMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public SummonerDmo getSummonerById(String id) {
        return summonerMapper.selectByPrimaryKey(id);
    }

    @Override
    public BaseResult querySummonerOverview(GetSummonerDto getSummonerDto) {
        SummonerResult summonerResult = new SummonerResult();
        SummonerDmo summonerDmo = summonerMapper.selectByCond(getSummonerDto);
        if (summonerDmo == null) {
            eventPublisher.publishEvent(new SummonerEvent(getSummonerDto.getName()));
            return BaseResult.fail("The summoner doesn't exist in the lib now.");
        }
        summonerResult.setSummonerDmo(summonerDmo);

        PositionDmo positionDmo = positionMapper.selectBySummonerId(summonerDmo.getId());
        if (positionDmo == null) {
            LogUtil.addEvent(getSummonerDto.toString());
        }
        summonerResult.setPositionDmo(positionDmo);
        return summonerResult;
    }

}
