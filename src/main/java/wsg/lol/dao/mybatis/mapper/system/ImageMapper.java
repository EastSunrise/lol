package wsg.lol.dao.mybatis.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.pojo.dto.general.ImageDto;

import java.util.List;

@Repository
@Mapper
public interface ImageMapper {

    ImageDto selectByPrimaryKey(Integer id);

    int deleteByGroup(ImageGroupEnum group);

    int batchInsert(List<ImageDto> imageDtoList);
}