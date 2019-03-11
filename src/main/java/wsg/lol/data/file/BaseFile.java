package wsg.lol.data.file;

import wsg.lol.common.constants.DefaultConfig;
import wsg.lol.common.utils.FileUtil;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-28 11:46
 */
public class BaseFile {

    protected static final String DATA_DIR = DefaultConfig.getDataDir();

    protected static final String CDN_LOCAL = FileUtil.concat2Path(DATA_DIR);

    protected static final String LATEST_VERSION = DefaultConfig.getLatestVersion();
}
