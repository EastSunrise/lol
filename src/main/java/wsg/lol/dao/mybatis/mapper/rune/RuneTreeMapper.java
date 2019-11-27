package wsg.lol.dao.mybatis.mapper.rune;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.domain.share.RuneTreeDo;
import wsg.lol.dao.mybatis.common.StaticMapper;

/**
 * Mapper interface for information of rune trees.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface RuneTreeMapper extends StaticMapper<RuneTreeDo> {
}