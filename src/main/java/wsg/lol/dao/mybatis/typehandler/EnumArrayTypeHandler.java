package wsg.lol.dao.mybatis.typehandler;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import wsg.lol.common.enums.champion.ChampionTagEnum;
import wsg.lol.common.enums.champion.ItemTagEnum;
import wsg.lol.common.enums.game.GameModeEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.util.EnumUtils;

import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom handler between the {@link Enum<T>} array and the string joining the {@link Enum#ordinal()} separated with {@link EnumArrayTypeHandler#SEPARATOR}
 *
 * @author Kingen
 */
@MappedTypes({
        ChampionTagEnum[].class, GameModeEnum[].class, ItemTagEnum[].class, MapEnum[].class
})
public class EnumArrayTypeHandler<T extends Enum<T>> extends BaseTypeHandler<T[]> {

    private static final String SEPARATOR = ",";

    private Class<T[]> clazz;

    public EnumArrayTypeHandler(Class<T[]> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T[] parameter, JdbcType jdbcType) throws SQLException {
        List<Integer> ordinals = new ArrayList<>();
        for (T t : parameter) {
            if (t != null) {
                ordinals.add(t.ordinal());
            }
        }
        ps.setString(i, StringUtils.join(ordinals, SEPARATOR));
    }

    @Override
    public T[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getEnums(rs.getString(columnName));
    }

    @Override
    public T[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getEnums(rs.getString(columnIndex));
    }

    @Override
    public T[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getEnums(cs.getString(columnIndex));
    }

    @SuppressWarnings("unchecked")
    private T[] getEnums(String string) {
        String[] parts = StringUtils.split(string, SEPARATOR);
        if (ArrayUtils.isEmpty(parts)) {
            return null;
        }

        Class<T> tClass = (Class<T>) clazz.getComponentType();
        T[] ts = (T[]) Array.newInstance(tClass, parts.length);
        for (int i = 0; i < parts.length; i++) {
            ts[i] = EnumUtils.parseEnumByOrdinal(Integer.parseInt(parts[i]), tClass);
        }
        return ts;
    }
}
