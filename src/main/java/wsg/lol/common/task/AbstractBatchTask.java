package wsg.lol.common.task;

import wsg.lol.common.base.Result;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Custom task to do in batches.
 *
 * @author Kingen
 */
public class AbstractBatchTask<T, R extends Result> extends RecursiveTask<R> {

    public static final int MAX_SIZE = 16000;

    private static final int MIN_SIZE = 1000;

    private List<T> tList;

    private MinTaskStrategy<T, R> minTaskStrategy;

    public AbstractBatchTask(List<T> tList, MinTaskStrategy<T, R> minTaskStrategy) {
        this.tList = tList;
        this.minTaskStrategy = minTaskStrategy;
    }

    @Override
    protected R compute() {
        int size = tList.size();
        if (size <= MIN_SIZE) {
            return minTaskStrategy.doMinTask(tList);
        } else {
            int mid = size / 2;
            List<T> leftList = tList.subList(0, mid);
            List<T> rightList = tList.subList(mid, size);
            AbstractBatchTask<T, R> leftTask = new AbstractBatchTask<>(leftList, minTaskStrategy);
            AbstractBatchTask<T, R> rightTask = new AbstractBatchTask<>(rightList, minTaskStrategy);
            leftTask.fork();
            rightTask.fork();
            return minTaskStrategy.joinResult(leftTask.join(), rightTask.join());
        }
    }

    public R getResult() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(this);
        return this.join();
    }
}
