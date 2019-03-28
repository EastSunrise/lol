package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.pojo.dmo.league.PositionDmo;
import wsg.lol.pojo.dto.api.league.LeagueListDto;

@Repository
@Mapper
public interface LeagueMapper {
    int deleteByPrimaryKey(String leagueId);

    int insert(LeagueListDto record);

    int insertSelective(LeagueListDto record);

    LeagueListDto selectByPrimaryKey(String leagueId);

    int updateByPrimaryKeySelective(LeagueListDto record);

    int updateByPrimaryKey(LeagueListDto record);

    PositionDmo selectBySummonerId(String summonerId);
}