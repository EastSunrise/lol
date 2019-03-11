package wsg.lol.dao.mapper;

import wsg.lol.dmo.champion.MasteryDmo;

public interface MasteryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MasteryDmo record);

    int insertSelective(MasteryDmo record);

    MasteryDmo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MasteryDmo record);

    int updateByPrimaryKey(MasteryDmo record);
}