package wsg.lol.dao.mybatis.mapper.champion;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.champion.ChampionTipDto;
import wsg.lol.dao.mybatis.config.StaticMapper;

/**
 * Mapper interface for tips of champions.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ChampionTipMapper extends StaticMapper<ChampionTipDto> {
}