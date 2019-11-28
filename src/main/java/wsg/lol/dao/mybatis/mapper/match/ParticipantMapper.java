package wsg.lol.dao.mybatis.mapper.match;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.pojo.domain.match.ParticipantDo;

/**
 * Mapper for participants in the match.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ParticipantMapper extends InsertListMapper<ParticipantDo> {
}