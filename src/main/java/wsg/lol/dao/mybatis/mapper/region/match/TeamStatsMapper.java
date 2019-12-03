package wsg.lol.dao.mybatis.mapper.region.match;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.pojo.domain.match.TeamStatsDo;

/**
 * Mapper for stats of the team in the match.
 *
 * @author Kingen
 */
@Repository
public interface TeamStatsMapper extends InsertListMapper<TeamStatsDo> {
}