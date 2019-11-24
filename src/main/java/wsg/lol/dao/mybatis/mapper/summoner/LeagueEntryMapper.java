package wsg.lol.dao.mybatis.mapper.summoner;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.dao.mybatis.config.StaticStrategy;

/**
 * Mapper for entries of the league.
 *
 * @author Kingen
 */
@Mapper
@Repository
public interface LeagueEntryMapper extends StaticStrategy<LeagueEntryDto> {
}