package wsg.lol.dao.mybatis.typehandler;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import wsg.lol.common.constant.DatabaseConst;
import wsg.lol.common.enums.champion.ChampionTagEnum;
import wsg.lol.common.enums.champion.ItemTagEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.util.EnumUtils;

import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Custom handler transferring enum array to string joined the ordinals of enums separated with <code>ORDINAL_SEPARATOR</code>
 */
@MappedTypes({
        ChampionTagEnum[].class, ItemTagEnum[].class, MapEnum[].class
})
public class EnumArrayTypeHandler<T extends Enum<T>> implements TypeHandler<T[]> {

    private Class<T> clazz;

    public EnumArrayTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.clazz = clazz;
        }
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, T[] enums, JdbcType jdbcType) throws SQLException {
        String str = null;
        if (enums != null) {
            String[] ordinals = new String[enums.length];
            for (int i = 0; i < enums.length; i++) {
                ordinals[i] = enums[i].ordinal() + "";
            }
            str = StringUtils.join(ordinals, DatabaseConst.ORDINAL_SEPARATOR);
        }

        preparedStatement.setObject(index, str, JdbcType.VARCHAR.TYPE_CODE);
    }

    @Override
    public T[] getResult(ResultSet resultSet, String columnLabel) throws SQLException {
        return getEnumsFromString(resultSet.getString(columnLabel));
    }

    @Override
    public T[] getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return getEnumsFromString(resultSet.getString(columnIndex));
    }

    @Override
    public T[] getResult(CallableStatement callableStatement, int parameterIndex) throws SQLException {
        return getEnumsFromString(callableStatement.getString(parameterIndex));
    }

    @SuppressWarnings("unchecked")
    private T[] getEnumsFromString(String value) {
        if (value == null) {
            return null;
        }
        String[] ordinals = StringUtils.split(value, DatabaseConst.ORDINAL_SEPARATOR);
        T[] enums = (T[]) Array.newInstance(clazz, ordinals.length);
        for (int i = 0; i < ordinals.length; i++) {
            enums[i] = EnumUtils.parseEnumByOrdinal(Integer.parseInt(ordinals[i]), clazz);
        }
        return enums;
    }
}
