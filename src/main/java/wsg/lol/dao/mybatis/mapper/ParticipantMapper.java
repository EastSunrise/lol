package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.pojo.dmo.match.ParticipantDmo;

import java.util.List;

@Repository
@Mapper
public interface ParticipantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ParticipantDmo record);

    int insertSelective(ParticipantDmo record);

    ParticipantDmo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ParticipantDmo record);

    int updateByPrimaryKey(ParticipantDmo record);

    int batchInsertParticipants(List<ParticipantDmo> participantDmoList);
}