package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.pojo.dmo.champion.MasteryDmo;

@Mapper
public interface MasteryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MasteryDmo record);

    int insertSelective(MasteryDmo record);

    MasteryDmo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MasteryDmo record);

    int updateByPrimaryKey(MasteryDmo record);
}