package wsg.lol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsg.lol.common.utils.LogUtil;
import wsg.lol.dao.mapper.SummonerMapper;
import wsg.lol.dmo.summoner.SummonerDmo;
import wsg.lol.dto.query.GetSummonerDto;
import wsg.lol.dto.result.SummonerResult;
import wsg.lol.service.intf.SummonerService;

import java.util.LinkedList;
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

    @Override
    public SummonerDmo getSummonerById(String id) {
        return summonerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void test() {
        List<String> list = new LinkedList<String>() {{
            add("0001");
            add("0003");
        }};
        List<String> list1 = summonerMapper.checkSummonersNotExist(list);
        for (String s : list1) {
            LogUtil.info(s);
        }
    }

    @Override
    public SummonerResult querySummonerOverview(GetSummonerDto getSummonerDto) {
        return null;
    }
}
