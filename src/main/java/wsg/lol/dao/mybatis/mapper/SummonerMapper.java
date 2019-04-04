package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mybatis.common.BaseMapper;
import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.dto.api.summoner.SummonerDto;

import java.util.List;

@Repository
@Mapper
public interface SummonerMapper extends BaseMapper<SummonerDmo> {

    int insertSummoner(SummonerDto summonerDto);

    List<String> removeSummonersExist(List<String> list);
}