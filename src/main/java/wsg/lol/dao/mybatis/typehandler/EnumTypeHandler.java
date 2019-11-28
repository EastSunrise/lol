package wsg.lol.dao.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import wsg.lol.common.enums.champion.ChampionTipEnum;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.enums.share.*;
import wsg.lol.common.enums.summoner.DivisionEnum;
import wsg.lol.common.enums.summoner.TierEnum;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.enums.system.PlatformRoutingEnum;
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
        ChampionTipEnum.class, SpellNumEnum.class,
        ImageGroupEnum.class,
        MapEnum.class, GameModeEnum.class, RecommendedTypeEnum.class, BlockTypeEnum.class,
        PlatformRoutingEnum.class,
        RankQueueEnum.class, TierEnum.class, DivisionEnum.class,
        EventTypeEnum.class, EventStatusEnum.class
})
public class EnumTypeHandler<T extends Enum<T>> extends BaseTypeHandler<T> {

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
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.ordinal(), JdbcType.TINYINT.TYPE_CODE);
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return EnumUtils.parseEnumByOrdinal(rs.getInt(columnName), clazz);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return EnumUtils.parseEnumByOrdinal(rs.getInt(columnIndex), clazz);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return EnumUtils.parseEnumByOrdinal(cs.getInt(columnIndex), clazz);
    }
}
