package wsg.lol.service.intf;

import wsg.lol.common.base.GenericResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.result.system.VersionResult;

/**
 * Service for system.
 *
 * @author Kingen
 */
public interface SystemService {

    /**
     * Check if the cdn directory exists.
     */
    GenericResult<Boolean> checkCdn(String version);

    /**
     * Get info of version.
     */
    VersionResult getVersion();

    /**
     * Update config of current version.
     */
    Result updateVersion(String version);

    /**
     * Check if the database has been initialize.
     */
    GenericResult<Boolean> isInitialized();

    /**
     * Update the initialization config.
     */
    Result initialize();
}
