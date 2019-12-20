package wsg.lol.service.common;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.base.ListResult;
import wsg.lol.common.base.Result;
import wsg.lol.common.constant.ErrorCodeConst;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.dao.mybatis.common.mapper.ClearMapper;
import wsg.lol.dao.mybatis.common.mapper.ReplaceListMapper;
import wsg.lol.dao.mybatis.common.mapper.StaticMapper;

import java.util.List;

/**
 * Common class for static strategy.
 *
 * @author Kingen
 */
public class ServiceExecutor {

    private static final Logger logger = LoggerFactory.getLogger(ServiceExecutor.class);

    public static <T extends BaseDo> Result updateStatic(StaticMapper<T> mapper, List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            logger.info("Collection is empty. Nothing updated.");
            return ResultUtils.success();
        }

        clear(mapper);
        int count = mapper.insertList(data);
        if (count != data.size()) {
            logger.error("Failed to insert the data");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info("{} inserted.", count);
        return ResultUtils.success();
    }

    private static void clear(ClearMapper<? extends BaseDo> strategy) {
        int count = strategy.clear();
        logger.info(count + " Cleared.");
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

    public static <T extends BaseDo> Result replaceList(ReplaceListMapper<T> mapper, List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            logger.info("Collection is empty. Nothing replaced.");
            return ResultUtils.success();
        }
        int count = mapper.replaceList(data);
        if (count != data.size()) {
            logger.error("Failed to replace the data");
            throw new AppException(ErrorCodeConst.DATABASE_ERROR);
        }
        logger.info("{} replaced.", count);
        return ResultUtils.success();
    }

    public static <T> ListResult<? extends T> retryUntilNotEmpty(ServiceTask<T> task) {
        for (long millis = 1000; ; millis *= 2) {
            List<? extends T> ts = task.run();
            if (CollectionUtils.isNotEmpty(ts)) {
                return ListResult.create(ts);
            }
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
