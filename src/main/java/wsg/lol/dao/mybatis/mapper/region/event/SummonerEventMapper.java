package wsg.lol.dao.mybatis.mapper.region.event;

import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.system.SummonerEventDo;
import wsg.lol.dao.mybatis.common.mapper.EventMapper;

/**
 * Mapper for events of summoners.
 *
 * @author Kingen
 */
@Repository("EventSummonerMapper")
public interface SummonerEventMapper extends EventMapper<SummonerEventDo> {
}
