package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dmo.champion.ChampionMasteryDmo;
import wsg.lol.common.pojo.dto.summoner.ChampionMasteryDto;
import wsg.lol.dao.mybatis.common.BaseMapper;

@Repository
@Mapper
public interface MasteryMapper extends BaseMapper<ChampionMasteryDmo> {

    ChampionMasteryDmo selectByUnionKey(String summonerId, Integer championId);

    int insertMastery(ChampionMasteryDto masteryDto);

    int updateByUnionKey(ChampionMasteryDto masteryDto);
}