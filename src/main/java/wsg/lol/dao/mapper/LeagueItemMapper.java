package wsg.lol.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.pojo.dmo.league.ItemDmo;

@Mapper
public interface LeagueItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemDmo record);

    int insertSelective(ItemDmo record);

    ItemDmo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemDmo record);

    int updateByPrimaryKey(ItemDmo record);
}