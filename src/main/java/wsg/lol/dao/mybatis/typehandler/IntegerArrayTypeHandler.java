package wsg.lol.dao.mybatis.typehandler;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Custom handler between the int array and the string joining the {@link Enum#ordinal()} separated with {@link IntegerArrayTypeHandler#SEPARATOR}
 *
 * @author Kingen
 */
@MappedTypes({
        Integer[].class
})
public class IntegerArrayTypeHandler implements TypeHandler<Integer[]> {

    private static final String SEPARATOR = ",";

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Integer[] ts, JdbcType jdbcType) throws SQLException {
        String str = StringUtils.join(ts, SEPARATOR);
        preparedStatement.setString(i, str);
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, String s) throws SQLException {
        return getIntegersFromString(resultSet.getString(s));
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, int i) throws SQLException {
        return getIntegersFromString(resultSet.getString(i));
    }

    @Override
    public Integer[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        return getIntegersFromString(callableStatement.getString(i));
    }

    private Integer[] getIntegersFromString(String value) {
        String[] parts = StringUtils.split(value, SEPARATOR);
        if (parts == null) {
            return null;
        }
        Integer[] integers = new Integer[parts.length];
        for (int i = 0; i < parts.length; i++) {
            integers[i] = Integer.parseInt(parts[i]);
        }
        return integers;
    }
}
