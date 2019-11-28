package wsg.lol.dao.mybatis.mapper.match;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.pojo.domain.match.ParticipantStatsDo;

/**
 * Mapper for stats of participants in the match.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ParticipantStatsMapper extends InsertListMapper<ParticipantStatsDo> {

}