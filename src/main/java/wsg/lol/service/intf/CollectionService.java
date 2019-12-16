package wsg.lol.service.intf;

import wsg.lol.common.base.Result;
import wsg.lol.common.enums.share.ImageGroupEnum;
import wsg.lol.common.pojo.dto.item.ImageDto;

import java.util.List;

/**
 * Service for collections.
 *
 * @author Kingen
 */
public interface CollectionService {

    /**
     * Update the data of items once the version changes.
     */
    Result updateItems(String version);

    /**
     * Update the data of reforged runes once the version changes.
     */
    Result updateRunes(String version);

    /**
     * Update the data of maps once the version changes.
     */
    Result updateMaps(String version);

    /**
     * Update the data of profile icons once the version changes.
     */
    Result updateProfileIcons(String version);

    /**
     * Update images of specified groups.
     */
    Result updateImages(List<ImageDto> images, ImageGroupEnum... groups);
}
