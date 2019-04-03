package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mybatis.common.MyMapper;
import wsg.lol.pojo.dmo.match.MatchReferenceDmo;
import wsg.lol.pojo.dto.api.match.MatchReferenceDto;

@Repository
@Mapper
public interface MatchReferenceMapper extends MyMapper<MatchReferenceDmo> {

    int insertReference(MatchReferenceDto referenceDto);
}