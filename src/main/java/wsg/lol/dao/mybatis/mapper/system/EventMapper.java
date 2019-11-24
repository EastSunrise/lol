package wsg.lol.dao.mybatis.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.base.Page;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;

import java.util.List;

/**
 * Mapper of the table of events.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface EventMapper {

    List<String> selectEventContext(EventTypeEnum type, EventStatusEnum status);

    int insertEventsOfType(EventTypeEnum type, EventStatusEnum status, List<String> contexts);

    List<String> selectEventContextByPage(EventTypeEnum type, EventStatusEnum status, Page page);

    int updateStatus(EventTypeEnum type, String context, EventStatusEnum from, EventStatusEnum to);
}