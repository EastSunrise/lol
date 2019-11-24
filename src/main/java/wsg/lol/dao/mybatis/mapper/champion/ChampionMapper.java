package wsg.lol.dao.mybatis.mapper.champion;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.champion.ChampionDto;
import wsg.lol.dao.mybatis.config.StaticStrategy;

/**
 * Mapper interface for base information of champions.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ChampionMapper extends StaticStrategy<ChampionDto> {
}