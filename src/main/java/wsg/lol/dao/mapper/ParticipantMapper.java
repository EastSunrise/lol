package wsg.lol.dao.mapper;

import wsg.lol.dmo.match.ParticipantDmo;

public interface ParticipantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ParticipantDmo record);

    int insertSelective(ParticipantDmo record);

    ParticipantDmo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ParticipantDmo record);

    int updateByPrimaryKey(ParticipantDmo record);
}