package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mybatis.common.MyMapper;
import wsg.lol.pojo.dmo.champion.ChampionMasteryDmo;
import wsg.lol.pojo.dto.api.champion.ChampionMasteryDto;

@Repository
@Mapper
public interface MasteryMapper extends MyMapper<ChampionMasteryDmo> {

    int insert(ChampionMasteryDto record);

    int updateByUnionKey(ChampionMasteryDto record);

    ChampionMasteryDmo selectByPrimaryKey(Integer id);

    ChampionMasteryDmo selectByUnionKey(String summonerId, Integer championId);
}