package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mybatis.common.MyMapper;
import wsg.lol.pojo.base.Page;
import wsg.lol.pojo.dmo.match.MatchReferenceDmo;
import wsg.lol.pojo.dto.api.match.MatchReferenceDto;

import java.util.List;

@Repository
@Mapper
public interface MatchMapper extends MyMapper<MatchReferenceDmo> {

    int insert(MatchReferenceDto referenceDto);

    int updateCheckedByKey(Integer id, boolean checked);

    List<MatchReferenceDmo> selectLastUncheckedMatches(Page page);

    MatchReferenceDmo selectByPrimaryKey(Integer id);
}