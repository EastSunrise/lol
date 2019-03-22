package wsg.lol.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.pojo.dmo.league.PositionDmo;

import java.util.List;

@Mapper
public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PositionDmo record);

    int insertSelective(PositionDmo record);

    PositionDmo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PositionDmo record);

    int updateByPrimaryKey(PositionDmo record);

    PositionDmo selectBySummonerId(String summonerId);

    int batchInsertPosition(List<PositionDmo> positionDmoList);
}