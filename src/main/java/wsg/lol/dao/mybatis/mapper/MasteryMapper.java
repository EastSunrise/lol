package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dmo.champion.ChampionMasteryDto;
import wsg.lol.common.pojo.dto.summoner.MasteryDto;
import wsg.lol.dao.mybatis.common.BaseMapper;

/**
 * // TODO: (Kingen, 2019/11/18)
 */
@Repository
@Mapper
public interface MasteryMapper extends BaseMapper<ChampionMasteryDto> {

    ChampionMasteryDto selectByUnionKey(String summonerId, Integer championId);

    int insertMastery(MasteryDto masteryDto);

    int updateByUnionKey(MasteryDto masteryDto);
}