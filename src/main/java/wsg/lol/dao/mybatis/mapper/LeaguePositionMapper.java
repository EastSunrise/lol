package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.enums.rank.PositionEnum;
import wsg.lol.common.enums.rank.RankQueueEnum;
import wsg.lol.common.pojo.dmo.league.LeaguePositionDmo;
import wsg.lol.common.pojo.dto.league.LeagueEntryDto;
import wsg.lol.dao.mybatis.common.BaseMapper;

@Repository
@Mapper
public interface LeaguePositionMapper extends BaseMapper<LeaguePositionDmo> {

    LeaguePositionDmo selectByUnionKey(String summonerId, RankQueueEnum queueType, PositionEnum position);

    int insertPosition(LeagueEntryDto positionDto);

    int updateByUnionKey(LeagueEntryDto positionDto);
}