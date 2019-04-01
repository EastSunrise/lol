package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mybatis.common.MyMapper;
import wsg.lol.pojo.dmo.league.LeagueDmo;
import wsg.lol.pojo.dto.api.league.LeagueListDto;

@Repository
@Mapper
public interface LeagueMapper extends MyMapper<LeagueDmo> {

    int insert(LeagueListDto record);

    LeagueDmo selectByPrimaryKey(String leagueId);
}