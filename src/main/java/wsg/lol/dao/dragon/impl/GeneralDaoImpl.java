package wsg.lol.dao.dragon.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wsg.lol.common.util.HttpHelper;
import wsg.lol.dao.dragon.intf.GeneralDao;

import java.util.List;

/**
 * @author Kingen
 */
@Component
public class GeneralDaoImpl implements GeneralDao {

    @Value("${dragon.url.realm}")
    private String realmUrl;

    @Value("${dragon.url.version}")
    private String versionUrl;

    @Override
    public List<String> getVersions() {
        return JSON.parseArray(HttpHelper.getString(versionUrl), String.class);
    }

    @Override
    public String getLatestVersion() {
        return "9.22.1";
        // TODO: (Kingen, 2019/11/20)
//        return getVersions().get(0);
    }
}
