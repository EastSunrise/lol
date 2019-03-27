package wsg.lol.dao.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import wsg.lol.dao.mongo.intf.MongoDao;
import wsg.lol.pojo.base.QueryStateDto;
import wsg.lol.pojo.base.StateBean;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 */
@Repository
public class MongoDaoImpl implements MongoDao {

    private MongoTemplate mongoTemplate;

    @Override
    public <T extends StateBean> void dropCollection(Class<T> clazz) {
        mongoTemplate.dropCollection(clazz);
    }

    @Override
    public <T extends StateBean> T insertDocument(T t) {
        return mongoTemplate.insert(t);
    }

    @Override
    public <T extends StateBean> Collection<T> insertDocuments(List<T> beanList) {
        return mongoTemplate.insertAll(beanList);
    }

    @Override
    public <T extends StateBean> T getCollectionById(Object id, Class<T> clazz) {
        return mongoTemplate.findById(id, clazz);
    }

    @Override
    public <T extends StateBean, V extends QueryStateDto> T getCollectionByCond(V cond, Class<T> clazz) {
        return mongoTemplate.findOne(getQueryByCond(cond), clazz);
    }

    @Override
    public <T extends StateBean, V extends QueryStateDto> List<T> getCollectionsByCond(V cond, Class<T> clazz) {
        return mongoTemplate.find(getQueryByCond(cond), clazz);
    }

    @Override
    public <T extends StateBean, V extends QueryStateDto> List<T> getCollections(Class<T> clazz) {
        return mongoTemplate.findAll(clazz);
    }

    /**
     * Create query by cond
     */
    private <V extends QueryStateDto> Query getQueryByCond(V cond) {
        Query query = new Query();
        Field[] fields = cond.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(cond);
                if (value != null) {
                    query.addCriteria(Criteria.where(field.getName()).is(value));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
