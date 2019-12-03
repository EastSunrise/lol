package wsg.lol.dao.mybatis.mapper.region.match;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.pojo.domain.match.MatchFrameDo;

/**
 * Mapper for each frame of the match.
 *
 * @author Kingen
 */
@Repository
public interface MatchFrameMapper extends InsertListMapper<MatchFrameDo> {
}