package wsg.lol.dao.mybatis.mapper.rune;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.rune.RuneTreeDto;
import wsg.lol.dao.mybatis.common.StateStrategy;

@Repository
@Mapper
public interface RuneTreeMapper extends StateStrategy<RuneTreeDto> {
    RuneTreeDto selectByPrimaryKey(Integer id);
}