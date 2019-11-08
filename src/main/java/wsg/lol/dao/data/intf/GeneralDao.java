package wsg.lol.dao.data.intf;

import wsg.lol.common.enums.route.RegionEnum;
import wsg.lol.common.pojo.dto.general.RealmDto;

import java.util.List;

/**
 * 全局数据接口
 *
 * @author wangsigen
 * @date 2019/11/7
 * @since 1.0
 */
public interface GeneralDao {

    /**
     * get realm
     */
    RealmDto getRealm(RegionEnum region);

    /**
     * 获取所有版本
     */
    List<String> getVersions();

    /**
     * 获取最新版本
     */
    String getLatestVersion();
}
