package wsg.lol.dao.mybatis.mapper.rune;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.rune.RuneDto;
import wsg.lol.dao.mybatis.common.StateStrategy;

@Repository
@Mapper
public interface RuneMapper extends StateStrategy<RuneDto> {

    RuneDto selectByPrimaryKey(Integer id);
}