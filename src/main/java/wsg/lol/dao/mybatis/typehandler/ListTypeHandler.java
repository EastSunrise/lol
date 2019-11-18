package wsg.lol.dao.mybatis.typehandler;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Custom handler between the list and the corresponding json string.
 *
 * @author Kingen
 */
@MappedTypes({
        List.class
})
public class ListTypeHandler<T> implements TypeHandler<List<T>> {

    private Class<T> clazz;

    public ListTypeHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, List<T> ts, JdbcType jdbcType) throws SQLException {
        String str = null;
        if (!CollectionUtils.isEmpty(ts)) {
            str = JSON.toJSONString(ts);
        }
        preparedStatement.setString(index, str);
    }

    @Override
    public List<T> getResult(ResultSet resultSet, String columnLabel) throws SQLException {
        return JSON.parseArray(resultSet.getString(columnLabel), clazz);
    }

    @Override
    public List<T> getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return JSON.parseArray(resultSet.getString(columnIndex), clazz);
    }

    @Override
    public List<T> getResult(CallableStatement callableStatement, int parameterIndex) throws SQLException {
        return JSON.parseArray(callableStatement.getString(parameterIndex), clazz);
    }
}
