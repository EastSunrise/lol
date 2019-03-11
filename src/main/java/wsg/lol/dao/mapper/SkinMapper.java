package wsg.lol.dao.mapper;

import wsg.lol.dmo.champion.SkinDmo;

public interface SkinMapper {
    int deleteByPrimaryKey(String id);

    int insert(SkinDmo record);

    int insertSelective(SkinDmo record);

    SkinDmo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SkinDmo record);

    int updateByPrimaryKey(SkinDmo record);
}