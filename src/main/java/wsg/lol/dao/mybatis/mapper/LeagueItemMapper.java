package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mybatis.common.MyMapper;
import wsg.lol.pojo.dmo.league.LeagueItemDmo;

@Repository
@Mapper
public interface LeagueItemMapper extends MyMapper<LeagueItemDmo> {

}