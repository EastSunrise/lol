package wsg.lol.dao.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mongo.TempUser;
import wsg.lol.dao.mongo.intf.ChampionDao;

/**
 * wsg
 *
 * @author wangsigen
 */
@Repository
public class ChampionDaoImpl implements ChampionDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(TempUser user) {
        mongoTemplate.save(user);
    }

    @Override
    public TempUser getUser(String name) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), TempUser.class);
    }
}
