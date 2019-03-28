package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.pojo.dmo.champion.ChampionMasteryDmo;
import wsg.lol.pojo.dto.api.champion.ChampionMasteryDto;

@Repository
@Mapper
public interface MasteryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChampionMasteryDto record);

    int insertSelective(ChampionMasteryDto record);

    ChampionMasteryDto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChampionMasteryDto record);

    int updateByUnionKey(ChampionMasteryDto record);

    ChampionMasteryDmo selectByUnionKey(String summonerId, Integer championId);
}