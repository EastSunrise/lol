package wsg.lol.service.intf;

import wsg.lol.common.base.Result;
import wsg.lol.common.enums.champion.ImageGroupEnum;
import wsg.lol.common.pojo.dto.share.ImageDto;

import java.util.List;

/**
 * Service for common data.
 *
 * @author Kingen
 */
public interface SharedService {

    /**
     * Update images of specified groups.
     */
    Result updateImages(List<ImageDto> images, ImageGroupEnum... groups);

    /**
     * Update the data of maps once the version changes.
     */
    Result updateMaps(String version);

    /**
     * Update the data of profile icons once the version changes.
     */
    Result updateProfileIcons(String version);

    /**
     * Update the data of shared status.
     */
    Result updateSharedStatus();

    /**
     * Update the rotation of champions.
     */
    Result updateChampionRotation();
}
