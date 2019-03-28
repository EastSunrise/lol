package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.pojo.dmo.league.PositionDmo;
import wsg.lol.pojo.dto.api.league.LeaguePositionDto;
import wsg.lol.pojo.enums.impl.code.PositionEnum;
import wsg.lol.pojo.enums.impl.code.RankQueueEnum;

@Repository
@Mapper
public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaguePositionDto record);

    int insertSelective(PositionDmo record);

    PositionDmo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PositionDmo record);

    int updateByUnionKey(LeaguePositionDto record);

    PositionDmo selectBySummonerId(String summonerId);

    PositionDmo selectByUnionKey(String summonerId, RankQueueEnum queueType, PositionEnum position);
}