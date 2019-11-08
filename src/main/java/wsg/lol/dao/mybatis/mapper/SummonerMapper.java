package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.dao.mybatis.common.BaseMapper;

import java.util.List;

@Repository
@Mapper
public interface SummonerMapper extends BaseMapper<SummonerDmo> {

    int insertSummoner(SummonerDto summonerDto);

    List<String> removeSummonersExist(List<String> list);
}