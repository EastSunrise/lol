package wsg.lol.dao.mongo.intf;

import wsg.lol.pojo.base.IJson;
import wsg.lol.pojo.base.QueryDto;

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
    <T extends IJson> void dropCollection(Class<T> clazz);

    /**
     * Insert a document.
     */
    <T extends IJson> T insertDocument(T t);

    /**
     * Batch insert list of documents.
     */
    <T extends IJson> Collection<T> insertDocuments(List<T> beanList);

    /**
     * Get the certain beam specified by id.
     */
    <T extends IJson> T getCollectionById(Object id, Class<T> clazz);

    /**
     * Get the certain bean specified by condition.
     */
    <T extends IJson, Q extends QueryDto> T getCollectionByCond(Q cond, Class<T> clazz);

    /**
     * Get the beans specified by condition.
     */
    <T extends IJson, Q extends QueryDto> List<T> getCollectionsByCond(Q cond, Class<T> clazz);

    /**
     * Get all the beans.
     */
    <T extends IJson> List<T> getCollections(Class<T> clazz);
}
