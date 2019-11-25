package wsg.lol.dao.mybatis.mapper.champion;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.pojo.dto.champion.SpellDto;

/**
 * Mapper interface for spells of champions.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface SpellMapper extends InsertListMapper<SpellDto> {
    int deleteByNum(SpellNumEnum num);
}