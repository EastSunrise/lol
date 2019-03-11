package wsg.lol.dao.mapper;

import wsg.lol.dmo.league.LeagueDmo;

public interface LeagueMapper {
    int deleteByPrimaryKey(String leagueId);

    int insert(LeagueDmo record);

    int insertSelective(LeagueDmo record);

    LeagueDmo selectByPrimaryKey(String leagueId);

    int updateByPrimaryKeySelective(LeagueDmo record);

    int updateByPrimaryKey(LeagueDmo record);
}