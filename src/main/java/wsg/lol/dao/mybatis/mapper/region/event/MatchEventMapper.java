package wsg.lol.dao.mybatis.mapper.region.event;

import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.system.MatchEventDo;
import wsg.lol.dao.mybatis.common.mapper.EventMapper;

/**
 * Mapper for events of matches.
 *
 * @author Kingen
 */
@Repository("EventMatchMapper")
public interface MatchEventMapper extends EventMapper<MatchEventDo> {
}
