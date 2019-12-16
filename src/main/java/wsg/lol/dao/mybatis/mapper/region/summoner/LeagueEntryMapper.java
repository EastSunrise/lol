package wsg.lol.dao.mybatis.mapper.region.summoner;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.pojo.domain.summoner.LeagueEntryDo;
import wsg.lol.dao.mybatis.common.mapper.ReplaceListMapper;

/**
 * Mapper for entries of the league.
 *
 * @author Kingen
 */
@Repository
public interface LeagueEntryMapper extends InsertListMapper<LeagueEntryDo>, ReplaceListMapper<LeagueEntryDo> {
}