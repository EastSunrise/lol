package wsg.lol.service.common;

import wsg.lol.common.base.Result;

import java.util.List;

/**
 * Strategy to do the minimum task.
 *
 * @author Kingen
 */
public interface MinTaskStrategy<C, R extends Result> {

    /**
     * Doing the task.
     */
    R doMinTask(C c);

    /**
     * Merge the result.
     */
    R joinResult(List<R> rs);

    /**
     * Get the length of the Collection.
     */
    int getLength(C c);

    /**
     * Split the collection.
     */
    List<C> split(C c);
}
