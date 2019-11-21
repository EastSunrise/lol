package wsg.lol.service.intf;

import wsg.lol.common.base.Result;
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.pojo.dto.general.ImageDto;

import java.util.List;

/**
 * Service for system.
 *
 * @author Kingen
 */
public interface SystemService {

    /**
     * Update images of specified groups.
     */
    Result updateImages(List<ImageDto> imageDtoList, ImageGroupEnum... groups);

    /**
     * Update the data of maps once the version changes.
     */
    Result updateMaps(String version);

    /**
     * Update the data of profile icons once the version changes.
     */
    Result updateProfileIcons(String version);
}
