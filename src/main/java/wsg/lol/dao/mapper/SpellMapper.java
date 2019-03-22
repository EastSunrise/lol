package wsg.lol.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.pojo.dmo.champion.SpellDmo;

import java.util.List;

@Mapper
public interface SpellMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpellDmo record);

    int insertSelective(SpellDmo record);

    SpellDmo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpellDmo record);

    int updateByPrimaryKey(SpellDmo record);

    List<SpellDmo> selectSpellsByChampion(Integer championId);
}