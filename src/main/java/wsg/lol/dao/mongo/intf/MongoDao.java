package wsg.lol.dao.mongo.intf;

import wsg.lol.pojo.base.QueryStateDto;
import wsg.lol.pojo.base.StateBean;

import java.util.Collection;
import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface MongoDao {

    /**
     * Clear the collection specified by class.
     */
    <T extends StateBean> void dropCollection(Class<T> clazz);

    /**
     * Insert a document.
     */
    <T extends StateBean> T insertDocument(T t);

    /**
     * Batch insert list of documents.
     */
    <T extends StateBean> Collection<T> insertDocuments(List<T> beanList);

    /**
     * Get the certain beam specified by id.
     */
    <T extends StateBean> T getCollectionById(Object id, Class<T> clazz);

    /**
     * Get the certain bean specified by condition.
     */
    <T extends StateBean, V extends QueryStateDto> T getCollectionByCond(V cond, Class<T> clazz);

    /**
     * Get the beans specified by condition.
     */
    <T extends StateBean, V extends QueryStateDto> List<T> getCollectionsByCond(V cond, Class<T> clazz);

    /**
     * Get all the beans.
     */
    <T extends StateBean, V extends QueryStateDto> List<T> getCollections(Class<T> clazz);
}
