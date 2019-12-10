package wsg.lol.common.task;

import wsg.lol.common.base.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Custom task to do in batches.
 *
 * @author Kingen
 */
public class AbstractBatchTask<C, R extends Result> extends RecursiveTask<R> {

    public static final int MAX_SIZE = 16000;

    private static final int MIN_SIZE = 1000;

    private C c;

    private MinTaskStrategy<C, R> minTaskStrategy;

    public AbstractBatchTask(C c, MinTaskStrategy<C, R> minTaskStrategy) {
        this.c = c;
        this.minTaskStrategy = minTaskStrategy;
    }

    @Override
    protected R compute() {
        int length = minTaskStrategy.getLength(c);
        if (length <= MIN_SIZE) {
            return minTaskStrategy.doMinTask(c);
        } else {
            List<C> cs = minTaskStrategy.split(c);
            List<AbstractBatchTask<C, R>> tasks = new ArrayList<>();
            for (C c1 : cs) {
                tasks.add(new AbstractBatchTask<>(c1, minTaskStrategy));
            }
            for (AbstractBatchTask<C, R> task : tasks) {
                task.fork();
            }
            List<R> rs = new ArrayList<>();
            for (AbstractBatchTask<C, R> task : tasks) {
                rs.add(task.join());
            }
            return minTaskStrategy.joinResult(rs);
        }
    }

    public R getResult() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(this);
        return this.join();
    }
}
