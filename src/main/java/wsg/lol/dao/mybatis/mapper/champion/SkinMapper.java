package wsg.lol.dao.mybatis.mapper.champion;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.pojo.dto.champion.SkinDto;
import wsg.lol.dao.mybatis.common.StaticStrategy;

/**
 * Mapper interface for skins of champions.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface SkinMapper extends StaticStrategy<SkinDto> {

    SkinDto selectByPrimaryKey(String id);
}