package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mybatis.common.MyMapper;
import wsg.lol.pojo.dmo.league.LeaguePositionDmo;
import wsg.lol.pojo.dto.api.league.LeaguePositionDto;
import wsg.lol.pojo.enums.impl.code.PositionEnum;
import wsg.lol.pojo.enums.impl.code.RankQueueEnum;

import java.util.List;

@Repository
@Mapper
public interface LeaguePositionMapper extends MyMapper<LeaguePositionDmo> {

    int insert(LeaguePositionDto record);

    int updateByUnionKey(LeaguePositionDto record);

    List<LeaguePositionDmo> selectListBySummonerId(String summonerId);

    LeaguePositionDmo selectByUnionKey(String summonerId, RankQueueEnum queueType, PositionEnum position);
}