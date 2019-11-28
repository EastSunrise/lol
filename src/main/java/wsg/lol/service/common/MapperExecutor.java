package wsg.lol.service.common;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.mybatis.common.ClearMapper;
import wsg.lol.dao.mybatis.common.StaticMapper;

import java.util.List;

/**
 * Common class for static strategy.
 *
 * @author Kingen
 */
public class MapperExecutor {

    private static final Logger logger = LoggerFactory.getLogger(MapperExecutor.class);

    public static <T extends BaseDo> Result updateStatic(StaticMapper<T> mapper, List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            logger.info("Collection is empty. Nothing updated.");
            return ResultUtils.success();
        }

        ResultUtils.assertSuccess(clear(mapper));
        int count = mapper.insertList(data);
        if (count != data.size()) {
            logger.error("Failed to insert the data");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info("{} inserted.", count);
        return ResultUtils.success();
    }

    private static Result clear(ClearMapper<? extends BaseDo> strategy) {
        int count = strategy.clear();
        logger.info(count + " Cleared.");
        return ResultUtils.success();
    }

    public static <T extends BaseDo> Result insertList(InsertListMapper<T> mapper, List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            logger.info("Collection is empty. Nothing inserted.");
            return ResultUtils.success();
        }
        int count = mapper.insertList(data);
        if (count != data.size()) {
            logger.error("Failed to insert the data");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info("{} inserted.", count);
        return ResultUtils.success();
    }

    public static <T extends BaseDo> Result updateList(UpdateByPrimaryKeySelectiveMapper<T> mapper, List<T> data) {
        for (T t : data) {
            int count = mapper.updateByPrimaryKeySelective(t);
            if (count != 1) {
                logger.error("Failed to update {}", t);
                throw new AppException(ErrorCodeConst.DATABASE_ERROR);
            }
        }
        return ResultUtils.success();
    }
}
