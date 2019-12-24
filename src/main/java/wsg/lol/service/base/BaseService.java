package wsg.lol.service.base;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.rowbounds.SelectByExampleRowBoundsMapper;
import tk.mybatis.mapper.entity.Example;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.pojo.transfer.ObjectTransfer;
import wsg.lol.dao.mybatis.common.mapper.ClearMapper;
import wsg.lol.dao.mybatis.common.mapper.ReplaceListMapper;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Base class for all service. Provide common methods.
 *
 * @author Kingen
 */
public class BaseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Insert a new record.
     */
    protected <T extends BaseDo> void insert(InsertMapper<T> mapper, T t) {
        int count = mapper.insert(t);
        if (count != 1) {
            throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to inert the record " + t).error(logger);
        }
    }

    /**
     * Insert list of data.
     */
    protected <T extends BaseDo> int insertList(@NotNull InsertListMapper<T> mapper, List<T> ts) {
        if (CollectionUtils.isEmpty(ts)) {
            logger.info("Collection is empty. None inserted.");
            return 0;
        }

        int count = mapper.insertList(ts);
        if (count != ts.size()) {
            throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to insert the data").error(logger);
        }
        logger.info("{} inserted.", count);
        return count;
    }

    /**
     * Clear the table.
     */
    protected void clear(@NotNull ClearMapper<? extends BaseDo> mapper) {
        int count = mapper.clear();
        logger.info(count + " Cleared.");
    }

    /**
     * Update not-null properties of the record by specified condition.
     */
    protected <T extends BaseDo> void updateByPrimaryKeySelective(UpdateByPrimaryKeySelectiveMapper<T> mapper, T t) {
        int count = mapper.updateByPrimaryKeySelective(t);
        if (count != 1) {
            throw new AppException(ErrorCodeConst.DATABASE_ERROR, "Failed to update the record " + t).error(logger);
        }
    }

    /**
     * Replace list of data. Update the record if exists, otherwise insert it.
     */
    protected <T extends BaseDo> int replaceList(@NotNull ReplaceListMapper<T> mapper, List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            logger.info("Collection is empty. None replaced.");
            return 0;
        }

        int count = mapper.replaceList(data);
        if (count != data.size()) {
            logger.error("Failed to replace the data");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info("{} replaced.", count);
        return count;
    }

    /**
     * Select one record by the specified condition.
     */
    protected <C extends BaseDo, R extends BaseDto> R selectOne(@NotNull SelectOneMapper<C> mapper, @NotNull C cond, Class<R> clazz) {
        return ObjectTransfer.transferDo(mapper.selectOne(cond), clazz);
    }

    /**
     * Select by specified condition and row bounds.
     */
    protected <T extends BaseDo> List<T> selectByExampleAndRowBounds(@NotNull SelectByExampleRowBoundsMapper<T> mapper, Example example, RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }
}
