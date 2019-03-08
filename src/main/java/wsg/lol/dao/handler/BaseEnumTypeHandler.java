package wsg.lol.dao.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import wsg.lol.common.enums.impl.others.SpellTypeEnum;
import wsg.lol.common.enums.intf.BaseEnum;
import wsg.lol.common.utils.EnumUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-08 11:37
 */
@MappedTypes({SpellTypeEnum.class})
public class BaseEnumTypeHandler<V, E extends Enum & BaseEnum<V>> extends BaseTypeHandler<E> {

    private Class<E> clazz;

    public BaseEnumTypeHandler(Class<E> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.clazz = clazz;
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int index, E e, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            preparedStatement.setObject(index, e.getValue());
        } else {
            preparedStatement.setObject(index, e.getValue(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        V value = (V) resultSet.getObject(columnName);
        return resultSet.wasNull() ? null : EnumUtil.parseFromValue(value, this.clazz);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        V value = (V) resultSet.getObject(columnIndex);
        return resultSet.wasNull() ? null : EnumUtil.parseFromValue(value, this.clazz);
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        V value = (V) callableStatement.getObject(columnIndex);
        return callableStatement.wasNull() ? null : EnumUtil.parseFromValue(value, this.clazz);
    }
}
