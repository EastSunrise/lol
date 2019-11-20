package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.enums.rank.PositionEnum;
import wsg.lol.common.enums.rank.RankQueueEnum;
import wsg.lol.common.pojo.dmo.league.LeagueEntryDto;
import wsg.lol.dao.mybatis.common.BaseMapper;

/**
 * // TODO: (Kingen, 2019/11/18)
 */
@Repository
@Mapper
public interface LeaguePositionMapper extends BaseMapper<LeagueEntryDto> {

    LeagueEntryDto selectByUnionKey(String summonerId, RankQueueEnum queueType, PositionEnum position);

    int insertPosition(wsg.lol.common.pojo.dto.league.LeagueEntryDto positionDto);

    int updateByUnionKey(wsg.lol.common.pojo.dto.league.LeagueEntryDto positionDto);
}