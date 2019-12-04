package wsg.lol.dao.mybatis.mapper.region.summoner;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.rowbounds.SelectByExampleRowBoundsMapper;
import wsg.lol.common.pojo.domain.summoner.SummonerDo;

import java.util.Date;

/**
 * Mapper for basic data of summoners.
 *
 * @author Kingen
 */
@Repository
public interface SummonerMapper extends SelectByPrimaryKeyMapper<SummonerDo>, InsertMapper<SummonerDo>,
        SelectByExampleRowBoundsMapper<SummonerDo>, UpdateByPrimaryKeySelectiveMapper<SummonerDo>, SelectOneMapper<SummonerDo> {
    int updateLastMatch(String accountId, Date lastMatch);
}