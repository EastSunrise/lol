package wsg.lol.dao.mybatis.typehandler;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract handler between {@link T} array and the string joining the {@link #toString(T)} array separated with {@link #getSeparator()}
 *
 * @author Kingen
 */
public abstract class AbstractArrayTypeHandler<T> implements TypeHandler<T[]> {

    protected Class<T> clazz;

    AbstractArrayTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Array type argument cannot be null.");
        } else {
            this.clazz = clazz;
        }
    }

    protected abstract String toString(@NotNull T t);

    protected abstract T parseObject(@NotNull String string);

    String getSeparator() {
        return ",";
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, T[] ts, JdbcType jdbcType) throws SQLException {
        String str = null;
        if (!ArrayUtils.isEmpty(ts)) {
            List<String> list = new ArrayList<>();
            for (T t : ts) {
                list.add(t == null ? null : toString(t));
            }
            str = StringUtils.join(list, getSeparator());
        }
        preparedStatement.setString(index, str);
    }

    @Override
    public T[] getResult(ResultSet resultSet, String s) throws SQLException {
        return getObjects(resultSet.getString(s));
    }

    @Override
    public T[] getResult(ResultSet resultSet, int i) throws SQLException {
        return getObjects(resultSet.getString(i));
    }

    @Override
    public T[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        return getObjects(callableStatement.getString(i));
    }

    @SuppressWarnings("unchecked")
    private T[] getObjects(String str) {
        String[] parts = StringUtils.split(str, getSeparator());
        if (parts == null) {
            return null;
        }

        T[] ts = (T[]) Array.newInstance(clazz, parts.length);
        for (int i = 0; i < parts.length; i++) {
            ts[i] = parts[i] == null ? null : parseObject(parts[i]);
        }
        return ts;
    }
}
