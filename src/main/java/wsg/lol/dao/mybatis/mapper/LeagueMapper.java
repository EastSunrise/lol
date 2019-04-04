package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mybatis.common.BaseMapper;
import wsg.lol.pojo.dmo.league.LeagueDmo;

@Repository
@Mapper
public interface LeagueMapper extends BaseMapper<LeagueDmo> {

}