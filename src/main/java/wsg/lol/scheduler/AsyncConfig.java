package wsg.lol.scheduler;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Configuration to execute scheduler async.
 *
 * @author Kingen
 */
@Configuration
@EnableAsync
@PropertySource(value = {"classpath:config/task.properties"})
public class AsyncConfig {

    @Value("${task.corePoolSize}")
    private int corePoolSize;

    @Value("${task.maxPoolSize}")
    private int maxPoolSize;

    @Value("${task.queueCapacity}")
    private int queueCapacity;

    @Value("${task.keepAliveSeconds}")
    private int keepAliveSeconds;

    static final long FIXED_DELAY = DateUtils.MILLIS_PER_MINUTE;
    static final long INITIAL_DELAY = DateUtils.MILLIS_PER_SECOND;

    @Bean
    public Executor schedulerExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.initialize();
        return executor;
    }
}
