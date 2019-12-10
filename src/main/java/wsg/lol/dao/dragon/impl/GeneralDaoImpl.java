package wsg.lol.dao.dragon.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.util.HttpHelper;
import wsg.lol.config.DragonConfig;
import wsg.lol.dao.dragon.intf.GeneralDao;

import java.util.List;

/**
 * @author Kingen
 */
@Component
public class GeneralDaoImpl implements GeneralDao {

    private DragonConfig dragonConfig;

    @Override
    public List<String> getVersions() {
        return JSON.parseArray(HttpHelper.getString(dragonConfig.getUrl().getVersion()), String.class);
    }

    @Override
    public String getLatestVersion() {
        return getVersions().get(0);
    }

    @Autowired
    public void setDragonConfig(DragonConfig dragonConfig) {
        this.dragonConfig = dragonConfig;
    }
}
