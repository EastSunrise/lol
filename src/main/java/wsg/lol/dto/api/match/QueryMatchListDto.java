package wsg.lol.dto.api.match;

import wsg.lol.common.base.QueryDto;
import wsg.lol.common.constants.annotation.Optional;

import java.util.List;

/**
 * @author King
 * @date 2019/2/12
 */
public class QueryMatchListDto extends QueryDto {

    public static final int MAX_INDEX_RANGE = 100;

    public static final long MAX_TIME_RANGE = 7 * 24 * 60 * 60 * 1000L;

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
     * Set of season IDs for filtering the matchlist.
     */
    @Optional
    private List<Integer> season;

    /**
     * The end and begin time to use for filtering matchlist specified as epoch milliseconds. If beginTime is
     * specified, but not endTime, then endTime defaults to the the current unix timestamp in milliseconds (the
     * maximum time range limitation is not observed in this specific case). If endTime is specified, but not
     * beginTime, then beginTime defaults to the start of the account's match history returning a 400 due to the
     * maximum time range limitation. If both are specified, then endTime should be greater than beginTime. The
     * maximum time range allowed is one week, otherwise a 400 error code is returned.
     */
    @Optional
    private Long endTime;
    @Optional
    private Long beginTime;

    /**
     * The end and begin index to use for filtering matchlist. If beginIndex is specified, but not endIndex, then
     * endIndex defaults to beginIndex+100. If endIndex is specified, but not beginIndex, then beginIndex defaults to
     * 0. If both are specified, then endIndex must be greater than beginIndex. The maximum range allowed is 100,
     * otherwise a 400 error code is returned.
     */
    @Optional
    private Long endIndex;
    @Optional
    private Long beginIndex;

    public List<Integer> getChampion() {
        return champion;
    }

    public void setChampion(List<Integer> champion) {
        this.champion = champion;
    }

    public List<Integer> getQueue() {
        return queue;
    }

    public void setQueue(List<Integer> queue) {
        this.queue = queue;
    }

    public List<Integer> getSeason() {
        return season;
    }

    public void setSeason(List<Integer> season) {
        this.season = season;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Long endIndex) {
        this.endIndex = endIndex;
    }

    public Long getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(Long beginIndex) {
        this.beginIndex = beginIndex;
    }
}
