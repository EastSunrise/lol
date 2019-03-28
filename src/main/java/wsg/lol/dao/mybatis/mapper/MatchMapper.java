package wsg.lol.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.pojo.base.Page;
import wsg.lol.pojo.dmo.match.MatchDmo;
import wsg.lol.pojo.dmo.match.MatchReferenceDmo;
import wsg.lol.pojo.dto.api.match.MatchReferenceDto;

import java.util.List;

@Repository
@Mapper
public interface MatchMapper {
    int deleteByPrimaryKey(Long gameId);

    int insertIgnore(MatchDmo record);

    int insertSelective(MatchDmo record);

    int updateByPrimaryKeySelective(MatchDmo record);

    int updateByPrimaryKey(MatchDmo record);

    List<Long> checkMatchesNotExist(List<Long> idList);

    int insert(MatchReferenceDto referenceDto);

    List<MatchReferenceDmo> queryLastUncheckedMatches(Page page);
}