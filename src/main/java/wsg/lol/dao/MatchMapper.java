package wsg.lol.dao;

import wsg.lol.pojo.dmo.match.MatchDmo;

public interface MatchMapper {
    int deleteByPrimaryKey(Long gameId);

    int insert(MatchDmo record);

    int insertSelective(MatchDmo record);

    MatchDmo selectByPrimaryKey(Long gameId);

    int updateByPrimaryKeySelective(MatchDmo record);

    int updateByPrimaryKey(MatchDmo record);
}