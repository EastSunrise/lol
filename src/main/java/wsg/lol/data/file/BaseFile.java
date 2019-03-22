package wsg.lol.data.file;

import wsg.lol.common.utils.FileUtil;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-28 11:46
 */
public class BaseFile {

    protected static final String DATA_DIR = "data";

    protected static final String CDN_LOCAL = FileUtil.concat2Path(DATA_DIR);

    protected static final String LATEST_VERSION = "9.4.1";
}
