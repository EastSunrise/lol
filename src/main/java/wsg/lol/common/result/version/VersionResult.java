package wsg.lol.common.result.version;

import wsg.lol.common.pojo.base.BaseResult;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class VersionResult extends BaseResult {

    private String currentVersion;

    private String latestVersion;

    public boolean isLatestVersion() {
        return currentVersion != null && currentVersion.equals(latestVersion);
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }
}
