package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dmo.match.MatchReferenceDmo;
import wsg.lol.common.pojo.dto.match.MatchReferenceDto;
import wsg.lol.dao.mybatis.common.BaseMapper;

/**
 * // TODO: (Kingen, 2019/11/18)
 */
@Repository
@Mapper
public interface MatchReferenceMapper extends BaseMapper<MatchReferenceDmo> {

    int insertReference(MatchReferenceDto referenceDto);
}