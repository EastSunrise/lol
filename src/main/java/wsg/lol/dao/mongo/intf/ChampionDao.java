package wsg.lol.dao.mongo.intf;

import wsg.lol.dao.mongo.TempUser;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface ChampionDao {

    void saveUser(TempUser user);

    TempUser getUser(String name);
}
