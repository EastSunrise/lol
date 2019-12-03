package wsg.lol.dao.mybatis.mapper.region.match;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.pojo.domain.match.ParticipantStatsDo;

/**
 * Mapper for stats of participants in the match.
 *
 * @author Kingen
 */
@Repository
public interface ParticipantStatsMapper extends InsertListMapper<ParticipantStatsDo> {
}