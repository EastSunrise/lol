package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.pojo.event.SummonerEvent;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-21 16:27
 */
@Mapper
public interface EventMapper {

    int insertSummonerEvent(SummonerEvent summonerEvent);
}
