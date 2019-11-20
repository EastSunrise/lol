package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dmo.summoner.SummonerDto;
import wsg.lol.dao.mybatis.common.BaseMapper;

import java.util.List;

/**
 * // TODO: (Kingen, 2019/11/18)
 */
@Repository
@Mapper
public interface SummonerMapper extends BaseMapper<SummonerDto> {

    int insertSummoner(wsg.lol.common.pojo.dto.summoner.SummonerDto summonerDto);

    List<String> removeSummonersExist(List<String> list);
}