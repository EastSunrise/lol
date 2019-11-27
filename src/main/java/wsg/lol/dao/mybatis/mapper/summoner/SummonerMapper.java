package wsg.lol.dao.mybatis.mapper.summoner;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper;
import tk.mybatis.mapper.common.rowbounds.SelectByExampleRowBoundsMapper;
import wsg.lol.common.SummonerDo;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;

import java.util.Date;

/**
 * Mapper for basic data of summoners.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface SummonerMapper extends SelectByPrimaryKeyMapper<SummonerDto>, InsertMapper<SummonerDo>, SelectByExampleRowBoundsMapper<SummonerDto>, UpdateByPrimaryKeyMapper<SummonerDo> {

    int updateLastMatch(String accountId, Date lastMatch);
}