package wsg.lol.dao.mybatis.mapper.region.match;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import wsg.lol.common.pojo.domain.match.MatchDo;

/**
 * Mapper for base info of a match.
 *
 * @author Kingen
 */
@Repository
public interface MatchMapper extends InsertMapper<MatchDo> {
}