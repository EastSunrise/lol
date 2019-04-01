package wsg.lol.dao.data.api.base.intf;

import wsg.lol.pojo.base.QueryDto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * wsg
 *
 * @author wangsigen
 */
public interface BaseApi {

    <Q extends QueryDto, T extends Serializable> T getObject(String apiRef, Map<String, Object> pathParams,
                                                             Q queryDto, Class<T> clazz);

    <T extends Serializable> T getObject(String apiRef, Map<String, Object> pathParams, Class<T> clazz);

    <T extends Serializable> T getObject(String apiRef, Class<T> clazz);

    <T extends Serializable> List<T> getArray(String apiRef, Map<String, Object> pathParams, Class<T> clazz);

    <T extends Serializable> List<T> getArray(String apiRef, Class<T> clazz);
}
