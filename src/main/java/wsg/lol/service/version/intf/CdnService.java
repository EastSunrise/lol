package wsg.lol.service.version.intf;

import wsg.lol.common.result.base.GenericResult;

public interface CdnService {

    /**
     * 获取cdn地址
     */
    GenericResult<String> getCdnAddr();
}
