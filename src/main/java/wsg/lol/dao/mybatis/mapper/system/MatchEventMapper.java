package wsg.lol.dao.mybatis.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.system.MatchEventDo;
import wsg.lol.dao.mybatis.common.EventMapper;

/**
 * Mapper for events of matches.
 *
 * @author Kingen
 */
@Repository("EventMatchMapper")
@Mapper
public interface MatchEventMapper extends EventMapper<MatchEventDo> {
}
