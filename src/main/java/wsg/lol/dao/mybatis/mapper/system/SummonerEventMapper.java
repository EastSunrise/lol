package wsg.lol.dao.mybatis.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.system.SummonerEventDo;
import wsg.lol.dao.mybatis.common.EventMapper;

/**
 * Mapper for events of summoners.
 *
 * @author Kingen
 */
@Repository("EventSummonerMapper")
@Mapper
public interface SummonerEventMapper extends EventMapper<SummonerEventDo> {
}
