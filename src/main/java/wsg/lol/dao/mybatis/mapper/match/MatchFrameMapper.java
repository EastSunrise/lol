package wsg.lol.dao.mybatis.mapper.match;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.pojo.domain.match.MatchFrameDo;

/**
 * Mapper for each frame of the match.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface MatchFrameMapper extends InsertListMapper<MatchFrameDo> {
}