package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.pojo.dmo.champion.SkinDmo;

import java.util.List;

@Mapper
public interface SkinMapper {
    int deleteByPrimaryKey(String id);

    int insert(SkinDmo record);

    int insertSelective(SkinDmo record);

    SkinDmo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SkinDmo record);

    int updateByPrimaryKey(SkinDmo record);

    List<SkinDmo> selectSkinsByChampion(Integer championId);
}