package wsg.lol.dao.mybatis.mapper.lol.rune;

import org.springframework.stereotype.Repository;
import wsg.lol.common.annotation.Platform;
import wsg.lol.common.pojo.domain.share.RuneTreeDo;
import wsg.lol.dao.mybatis.common.StaticMapper;

/**
 * Mapper interface for information of rune trees.
 *
 * @author Kingen
 */
@Platform
@Repository
public interface RuneTreeMapper extends StaticMapper<RuneTreeDo> {
}