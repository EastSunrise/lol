package wsg.lol.dao.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

/**
 * Type handler for {@link Duration}
 *
 * @author Kingen
 */
@MappedTypes({
        Duration.class
})
public class DurationTypeHandler extends BaseTypeHandler<Duration> {

    private static final Logger logger = LoggerFactory.getLogger(DurationTypeHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Duration duration, JdbcType jdbcType) throws SQLException {
        if (jdbcType != null) {
            ps.setObject(i, duration.toMillis(), jdbcType.TYPE_CODE);
        } else {
            ps.setObject(i, duration.toMillis());
        }
    }

    @Override
    public Duration getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getDuration(rs.getObject(columnName));
    }

    @Override
    public Duration getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getDuration(rs.getObject(columnIndex));
    }

    @Override
    public Duration getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getDuration(cs.getObject(columnIndex));
    }

    private Duration getDuration(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Long) {
            return Duration.ofMillis((Long) object);
        }
        if (object instanceof Integer) {
            return Duration.ofMillis(((Integer) object).longValue());
        }
        logger.error("Can't cast {} to {}", object, Duration.class);
        throw new AppException(ErrorCodeConst.ILLEGAL_ARGS);
    }
}
