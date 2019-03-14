package wsg.lol.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.dmo.Position;
import wsg.lol.dmo.league.PositionDmo;

import java.util.List;

@Mapper
public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    PositionDmo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    PositionDmo selectBySummonerId(String summonerId);

    int batchInsertPosition(List<PositionDmo> positionDmoList);
}