package wsg.lol.dao.mybatis.mapper.summoner;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;

/**
 * Mapper for basic data of summoners.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface SummonerMapper {

    int insert(SummonerDto summonerDto);

    SummonerDto selectById(String id);
}