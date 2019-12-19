package wsg.lol.dao.mybatis.mapper.lol.rune;

import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.item.RuneTreeDo;
import wsg.lol.dao.mybatis.common.mapper.StaticMapper;

/**
 * Mapper interface for information of rune trees.
 *
 * @author Kingen
 */
@Repository
public interface RuneTreeMapper extends StaticMapper<RuneTreeDo> {
}