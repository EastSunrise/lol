package wsg.lol.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.pojo.dmo.league.LeagueDmo;
import wsg.lol.pojo.dmo.league.PositionDmo;

@Mapper
public interface LeagueMapper {
    int deleteByPrimaryKey(String leagueId);

    int insert(LeagueDmo record);

    int insertSelective(LeagueDmo record);

    LeagueDmo selectByPrimaryKey(String leagueId);

    int updateByPrimaryKeySelective(LeagueDmo record);

    int updateByPrimaryKey(LeagueDmo record);

    PositionDmo selectBySummonerId(String summonerId);
}