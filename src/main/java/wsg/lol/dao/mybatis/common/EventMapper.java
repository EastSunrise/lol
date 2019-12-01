package wsg.lol.dao.mybatis.common;

import tk.mybatis.mapper.common.rowbounds.SelectByExampleRowBoundsMapper;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.pojo.domain.system.EventDo;

import java.util.List;

/**
 * Common mapper for events
 *
 * @author Kingen
 */
public interface EventMapper<T extends EventDo> extends SelectByExampleRowBoundsMapper<T> {

    int insertIgnoreList(List<String> ids, EventStatusEnum status);

    int updateStatus(String id, EventStatusEnum from, EventStatusEnum to);
}
