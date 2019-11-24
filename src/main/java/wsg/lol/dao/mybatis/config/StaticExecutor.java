package wsg.lol.dao.mybatis.config;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.util.ResultUtils;

import java.util.List;

/**
 * Common class for static strategy.
 *
 * @author Kingen
 */
public class StaticExecutor {

    private static final Logger logger = LoggerFactory.getLogger(StaticExecutor.class);

    public static <T> Result updateStatic(StaticStrategy<T> strategy, List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            logger.info("Collection is empty. Nothing updated.");
            return ResultUtils.success();
        }

        int count = strategy.clear();
        logger.info(count + " Cleared.");
        count = strategy.batchInsert(data);
        if (count != data.size()) {
            logger.error("Failed to insert the data");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info(count + " Inserted.");
        return ResultUtils.success();
    }

    public static <T> Result batchInsert(StaticStrategy<T> strategy, List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            logger.info("Collection is empty. Nothing inserted.");
            return ResultUtils.success();
        }
        int count = strategy.batchInsert(data);
        if (count != data.size()) {
            logger.error("Failed to insert the data");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info(count + " Inserted.");
        return ResultUtils.success();
    }
}
