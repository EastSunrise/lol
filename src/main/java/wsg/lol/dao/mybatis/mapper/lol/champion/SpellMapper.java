package wsg.lol.dao.mybatis.mapper.lol.champion;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.pojo.domain.champion.SpellDo;

/**
 * Mapper interface for spells of champions.
 *
 * @author Kingen
 */
@Platform
@Repository
public interface SpellMapper extends InsertListMapper<SpellDo> {
    int deleteByNum(SpellNumEnum num);
}