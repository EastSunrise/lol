package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.pojo.dmo.league.PositionDmo;
import wsg.lol.pojo.dto.api.league.LeaguePositionDto;
import wsg.lol.pojo.enums.impl.code.PositionEnum;
import wsg.lol.pojo.enums.impl.code.RankQueueEnum;

import java.util.List;

@Repository
@Mapper
public interface PositionMapper {

    int insert(LeaguePositionDto record);

    int updateByUnionKey(LeaguePositionDto record);

    List<PositionDmo> selectListBySummonerId(String summonerId);

    PositionDmo selectByUnionKey(String summonerId, RankQueueEnum queueType, PositionEnum position);
}