package wsg.lol.common.task;

import wsg.lol.common.base.Result;

import java.util.List;

/**
 * Strategy to do the minimum task.
 *
 * @author Kingen
 */
public interface MinTaskStrategy<T, R extends Result> {

    R doMinTask(List<T> tList);

    R joinResult(R r1, R r2);
}
