package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import wsg.lol.pojo.dmo.match.MatchDmo;

import java.util.List;

@Mapper
public interface MatchMapper {
    int deleteByPrimaryKey(Long gameId);

    int insertIgnore(MatchDmo record);

    int insertSelective(MatchDmo record);

    MatchDmo selectByPrimaryKey(Long gameId);

    int updateByPrimaryKeySelective(MatchDmo record);

    int updateByPrimaryKey(MatchDmo record);

    List<Long> checkMatchesNotExist(List<Long> idList);
}