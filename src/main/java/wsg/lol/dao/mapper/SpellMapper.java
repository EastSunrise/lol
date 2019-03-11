package wsg.lol.dao.mapper;

import wsg.lol.dmo.champion.SpellDmo;

public interface SpellMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpellDmo record);

    int insertSelective(SpellDmo record);

    SpellDmo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpellDmo record);

    int updateByPrimaryKey(SpellDmo record);
}