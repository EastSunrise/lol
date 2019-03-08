package wsg.lol.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.dto.api.match.MatchDto;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-07 17:22
 */
@Mapper
public interface MatchMapper {

    int batchInsertMatchIfNotExist(List<MatchDto> matchDtoList);
}
