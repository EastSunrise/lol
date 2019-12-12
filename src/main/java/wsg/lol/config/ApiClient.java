package wsg.lol.config;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.Uninterruptibles;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Client for api.
 *
 * @author Kingen
 */
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiClient implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);

    @Setter
    private double rate;

    @Setter
    @Getter
    private int timeout;

    @Setter
    private List<Token> tokens;

    private PriorityBlockingQueue<RGApi> apis = new PriorityBlockingQueue<>();

    /**
     * Get available api token.
     *
     * @return null if there isn't a valid token.
     */
    public synchronized String getToken() {
        RGApi api = apis.poll();
        if (api == null) {
            logger.error("Unavailable token from the queue.");
            throw new AppException(ErrorCodeConst.HTTPS_ERROR);
        }
        logger.info("Using the token of the account {}.", api.username);
        String token = api.acquire();
        apis.add(api);
        return token;
    }

    @Override
    public void afterPropertiesSet() {
        for (Token token : tokens) {
            if (StringUtils.isNotBlank(token.key)) {
                this.apis.add(new RGApi(token, rate));
            }
        }
    }

    /**
     * Update the status of the token to INVALID.
     */
    public void invalidate(String token) {
        for (RGApi api : apis) {
            if (api.token.equals(token)) {
                api.valid = false;
                break;
            }
        }
    }

    private static class RGApi implements Comparable<RGApi> {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        private String username;
        private String token;
        private boolean valid;
        private double maxPermits;
        private double stableIntervalMicros;
        private double storedPermits;
        private long nextFreeTicket;
        @MonotonicNonNull
        private volatile Object mutexDoNotUseDirectly;

        RGApi(Token token, double permitsPerSecond) {
            this.username = token.username;
            this.token = token.key;
            this.valid = true;
            synchronized (mutex()) {
                doSetRate(permitsPerSecond, stopwatch.elapsed(MICROSECONDS));
            }
        }

        private void doSetRate(double permitsPerSecond, long nowMicros) {
            this.stableIntervalMicros = SECONDS.toMicros(1L) / permitsPerSecond;
            maxPermits = 1 * permitsPerSecond;
            resync(nowMicros);
        }

        String acquire() {
            long microsToWait = reserve();
            if (microsToWait > 0) {
                Uninterruptibles.sleepUninterruptibly(microsToWait, MICROSECONDS);
            }
            return !StringUtils.isEmpty(this.token) && valid ? this.token : null;
        }

        private long reserve() {
            synchronized (mutex()) {
                return reserveAndGetWaitLength(stopwatch.elapsed(MICROSECONDS));
            }
        }

        private long reserveAndGetWaitLength(long nowMicros) {
            long momentAvailable = reserveEarliestAvailable(nowMicros);
            return max(momentAvailable - nowMicros, 0);
        }

        private long reserveEarliestAvailable(long nowMicros) {
            resync(nowMicros);
            long returnValue = nextFreeTicket;
            double storedPermitsToSpend = min(1, this.storedPermits);
            double freshPermits = 1 - storedPermitsToSpend;
            long waitMicros = (long) (freshPermits * stableIntervalMicros);

            this.nextFreeTicket += waitMicros;
            this.storedPermits -= storedPermitsToSpend;
            return returnValue;
        }

        private void resync(long nowMicros) {
            if (nowMicros > nextFreeTicket) {
                double newPermits = (nowMicros - nextFreeTicket) / stableIntervalMicros;
                storedPermits = min(maxPermits, storedPermits + newPermits);
                nextFreeTicket = nowMicros;
            }
        }

        private Object mutex() {
            Object mutex = mutexDoNotUseDirectly;
            if (mutex == null) {
                synchronized (this) {
                    mutex = mutexDoNotUseDirectly;
                    if (mutex == null) {
                        mutexDoNotUseDirectly = mutex = new Object();
                    }
                }
            }
            return mutex;
        }

        @Override
        public int compareTo(@NonNull RGApi api) {
            int compare = Boolean.compare(this.valid, api.valid);
            return compare == 0 ? Long.compare(this.nextFreeTicket, api.nextFreeTicket) : compare;
        }
    }

    @Setter
    private static class Token {
        private String username;
        private String key;
    }
}
