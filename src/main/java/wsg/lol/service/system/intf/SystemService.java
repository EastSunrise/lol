package wsg.lol.service.system.intf;

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
     * Send a message.
     */
    void sendMessage(String message);

    /**
     * Send messages of warning.
     */
    void sendWarnMessage(Result result);

    /**
     * Check if the database has been initialized.
     */
    GenericResult<Boolean> isDatabaseInitialized();

    /**
     * Update the initialization config.
     */
    Result initialized();
}
