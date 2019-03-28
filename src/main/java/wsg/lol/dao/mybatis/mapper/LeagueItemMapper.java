package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.pojo.dto.api.league.LeagueItemDto;

@Mapper
@Repository
public interface LeagueItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeagueItemDto record);

    int insertSelective(LeagueItemDto record);

    LeagueItemDto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeagueItemDto record);

    int updateByPrimaryKey(LeagueItemDto record);
}