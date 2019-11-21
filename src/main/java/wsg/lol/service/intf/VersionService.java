package wsg.lol.service.intf;

import wsg.lol.common.base.Result;
import wsg.lol.common.result.version.VersionResult;

/**
 * Service for version control.
 *
 * @author Kingen
 */
public interface VersionService {

    /**
     * Check if the cdn directory exists.
     */
    Result checkCdn(String version);

    /**
     * Get info of version.
     */
    VersionResult getVersion();

    /**
     * Update config of current version.
     */
    Result updateVersion(String version);
}
