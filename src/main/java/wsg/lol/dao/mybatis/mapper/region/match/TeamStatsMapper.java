package wsg.lol.dao.mybatis.mapper.region.match;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.pojo.domain.match.TeamStatsDo;

/**
 * Mapper for stats of the team in the match.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface TeamStatsMapper extends InsertListMapper<TeamStatsDo> {
}