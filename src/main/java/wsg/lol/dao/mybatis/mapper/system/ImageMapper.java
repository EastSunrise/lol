package wsg.lol.dao.mybatis.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.enums.share.ImageGroupEnum;
import wsg.lol.common.pojo.domain.share.ImageDo;

/**
 * Mapper interface for information of images.
 *
 * @author Kingen
 */
@Repository
@Mapper
public interface ImageMapper extends InsertListMapper<ImageDo> {
    int deleteByGroup(ImageGroupEnum group);
}