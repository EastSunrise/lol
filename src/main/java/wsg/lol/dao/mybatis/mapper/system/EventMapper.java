package wsg.lol.dao.mybatis.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.rowbounds.SelectRowBoundsMapper;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.pojo.domain.system.EventDo;

import java.util.List;

/**
 * Mapper of the table of events.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface EventMapper extends SelectRowBoundsMapper<EventDo> {

    int insertEventsOfType(EventTypeEnum type, EventStatusEnum status, List<String> contexts);

    int updateStatus(EventTypeEnum type, String context, EventStatusEnum from, EventStatusEnum to);
}