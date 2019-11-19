package wsg.lol.dao.mybatis.mapper.champion;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.pojo.dto.champion.SpellDto;

import java.util.List;

/**
 * Mapper interface for spells of champions.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface SpellMapper {
    int deleteByNum(SpellNumEnum num);

    int batchInsert(List<SpellDto> list);
}