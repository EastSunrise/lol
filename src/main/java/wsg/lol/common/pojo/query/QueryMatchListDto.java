package wsg.lol.common.pojo.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.time.DateUtils;
import wsg.lol.common.annotation.Optional;
import wsg.lol.common.base.AppException;
import wsg.lol.common.base.QueryDto;
import wsg.lol.common.constant.ErrorCodeConst;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Condition to query matched.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryMatchListDto extends QueryDto {

    public static final int MAX_INDEX_RANGE = 100;

    public static final long MAX_TIME_RANGE = DateUtils.MILLIS_PER_DAY * 7;

    /**
     * Set of champion IDs for filtering the matchlist.
     */
    @Optional
    private List<Integer> champion;

    /**
     * Set of queue IDs for filtering the matchlist.
     */
    @Optional
    private List<Integer> queue;

    /**
     * The end and begin time to use for filtering matchlist specified as epoch milliseconds. If beginTime is specified,
     * but not endTime, then endTime defaults to the the current unix timestamp in milliseconds (the maximum time range
     * limitation is not observed in this specific case). If endTime is specified, but not beginTime, then beginTime
     * defaults to the start of the account's match history returning a 400 due to the maximum time range limitation. If
     * both are specified, then endTime should be greater than beginTime. The maximum time range allowed is one week,
     * otherwise a 400 error code is returned.
     */
    @Optional
    private Long endTime;
    @Optional
    private Long beginTime;

    /**
     * The end and begin index to use for filtering matchlist. If beginIndex is specified, but not endIndex, then
     * endIndex defaults to beginIndex+100. If endIndex is specified, but not beginIndex, then beginIndex defaults to 0.
     * If both are specified, then endIndex must be greater than beginIndex. The maximum range allowed is 100, otherwise
     * a 400 error code is returned.
     */
    @Optional
    private Long endIndex;
    @Optional
    private Long beginIndex;

    public static QueryMatchListDto getInitialCond() {
        QueryMatchListDto queryMatchListDto = new QueryMatchListDto();
        queryMatchListDto.setBeginTime(getInitialBegin().getTime());
        return queryMatchListDto;
    }

    public static Date getInitialBegin() {
        try {
            return DateUtils.parseDate("2019-11-21 00:00:00", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new AppException(ErrorCodeConst.SYSTEM_ERROR);
        }
    }

    public boolean isValid() {
        return (beginIndex == null || endIndex == null || (beginIndex < endIndex && beginIndex + MAX_INDEX_RANGE >= endIndex))
                && (beginTime == null || endTime == null || (beginTime < endTime && beginTime + MAX_TIME_RANGE >= endTime));
    }
}
