package wsg.lol.dao.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import wsg.lol.common.enums.champion.*;
import wsg.lol.common.enums.game.GameModeEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.enums.rank.DivisionEnum;
import wsg.lol.common.enums.rank.RankQueueEnum;
import wsg.lol.common.enums.rank.TierEnum;
import wsg.lol.common.enums.route.PlatformRoutingEnum;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.util.EnumUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Custom handler between the enum and {@link Enum#ordinal()}.
 *
 * @author Kingen
 */
@MappedTypes({
        ImageGroupEnum.class,
        ChampionTipEnum.class, SpellNumEnum.class, MapEnum.class, GameModeEnum.class,
        RecommendedTypeEnum.class, BlockTypeEnum.class,
        EventTypeEnum.class, EventStatusEnum.class, PlatformRoutingEnum.class,
        RankQueueEnum.class, TierEnum.class, DivisionEnum.class

})
public class EnumTypeHandler<T extends Enum<T>> implements TypeHandler<T> {

    private Class<T> clazz;

    /**
     * Assign the class of the enum.
     */
    public EnumTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.clazz = clazz;
        }
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, T e, JdbcType jdbcType) throws SQLException {
        Integer integer = null;
        if (e != null) {
            integer = e.ordinal();
        }
        preparedStatement.setObject(index, integer, JdbcType.TINYINT.TYPE_CODE);
    }

    @Override
    public T getResult(ResultSet resultSet, String columnLabel) throws SQLException {
        return getEnumFromOrdinal(resultSet.getInt(columnLabel));
    }

    @Override
    public T getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return getEnumFromOrdinal(resultSet.getInt(columnIndex));
    }

    @Override
    public T getResult(CallableStatement callableStatement, int parameterIndex) throws SQLException {
        return getEnumFromOrdinal(callableStatement.getInt(parameterIndex));
    }

    private T getEnumFromOrdinal(Integer ordinal) {
        return ordinal == null ? null : EnumUtils.parseEnumByOrdinal(ordinal, clazz);
    }
}
