package wsg.lol.dao.mybatis.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.pojo.dto.share.ImageDto;

import java.util.List;

/**
 * Mapper interface for information of images.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ImageMapper {
    int deleteByGroup(ImageGroupEnum group);

    int batchInsert(List<ImageDto> imageDtoList);
}