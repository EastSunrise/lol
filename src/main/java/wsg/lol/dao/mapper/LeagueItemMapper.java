package wsg.lol.dao.mapper;

import wsg.lol.dmo.league.ItemDmo;

public interface LeagueItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemDmo record);

    int insertSelective(ItemDmo record);

    ItemDmo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemDmo record);

    int updateByPrimaryKey(ItemDmo record);
}