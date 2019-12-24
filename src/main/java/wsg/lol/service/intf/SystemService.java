package wsg.lol.service.intf;

import wsg.lol.common.pojo.dto.system.VersionDto;

/**
 * Service for system.
 *
 * @author Kingen
 */
public interface SystemService {

    /**
     * Check if the cdn directory exists.
     */
    boolean checkCdn(String version);

    /**
     * Get info of version.
     */
    VersionDto getVersion();

    /**
     * Update config of current version.
     */
    void updateVersion(String version);
}
