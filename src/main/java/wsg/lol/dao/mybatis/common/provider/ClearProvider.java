package wsg.lol.dao.mybatis.common.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import wsg.lol.dao.mybatis.common.mapper.ClearMapper;

/**
 * Sql provider for {@link ClearMapper}
 *
 * @author Kingen
 */
public class ClearProvider extends MapperTemplate {

    public ClearProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String clear(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        return SqlHelper.deleteFromTable(entityClass, tableName(entityClass)) + "where 1 = 1";
    }
}
