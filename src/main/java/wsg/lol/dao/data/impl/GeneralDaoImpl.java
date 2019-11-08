package wsg.lol.dao.data.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wsg.lol.common.enums.route.RegionEnum;
import wsg.lol.common.pojo.dto.general.RealmDto;
import wsg.lol.common.util.HttpHelper;
import wsg.lol.dao.data.intf.GeneralDao;

import java.util.List;

/**
 * @author wangsigen
 * @date 2019/11/7
 * @see <a href="https://developer.riotgames.com/docs/lol#data-dragon_realms-versions">Realms & Versions</a>
 * @since 2.4.9.3
 */
@Component
public class GeneralDaoImpl implements GeneralDao {

    @Value("${state.url.realm}")
    private String realmUrl;

    @Value("${state.url.version}")
    private String versionUrl;

    @Override
    public RealmDto getRealm(RegionEnum region) {
        String jsonStr = HttpHelper.getString(String.format(realmUrl, region.getCode()));
        // TODO: (wangsigen, 2019/11/8) parse
        return null;
    }

    @Override
    public List<String> getVersions() {
        return JSON.parseArray(HttpHelper.getString(versionUrl), String.class);
    }

    @Override
    public String getLatestVersion() {
        return getVersions().get(0);
    }
}
