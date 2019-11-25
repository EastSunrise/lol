package wsg.lol.dao.mybatis.mapper.rune;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.rune.RuneDto;
import wsg.lol.dao.mybatis.config.StaticMapper;

/**
 * Mapper interface for information of runes.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface RuneMapper extends StaticMapper<RuneDto> {
}