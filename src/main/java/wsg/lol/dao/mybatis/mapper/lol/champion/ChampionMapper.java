package wsg.lol.dao.mybatis.mapper.lol.champion;

import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.champion.ChampionDo;
import wsg.lol.dao.mybatis.common.mapper.StaticMapper;

/**
 * Mapper interface for base information of champions.
 *
 * @author Kingen
 */
@Repository
public interface ChampionMapper extends StaticMapper<ChampionDo> {
}