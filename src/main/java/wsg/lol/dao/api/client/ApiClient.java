package wsg.lol.dao.api.client;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.Uninterruptibles;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import wsg.lol.config.ApiConfig;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Client for api.
 *
 * @author Kingen
 */
public abstract class ApiClient {


    /**
     * Get the token of api.
     */
    abstract String getToken();

    /**
     * When occurred that the token was forbidden.
     */
    abstract void occurForbidden(String token);

    /**
     * Get a valid username.
     */
    public abstract String getUsername();

    /**
     * Get the relative path of the lib.
     */
    public abstract String getPath();

    protected static class RGApi {
        final Stopwatch stopwatch = Stopwatch.createStarted();

        @Getter
        String username;
        boolean valid;
        long nextFreeTicket;
        String token;
        private double maxPermits;
        private double stableIntervalMicros;
        private double storedPermits;
        @MonotonicNonNull
        private volatile Object mutexDoNotUseDirectly;

        protected RGApi(ApiConfig.Token token, double permitsPerSecond) {
            this.username = token.getUsername();
            this.token = token.getKey();
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

        protected String acquire() {
            long microsToWait = reserve();
            if (microsToWait > 0) {
                Uninterruptibles.sleepUninterruptibly(microsToWait, MICROSECONDS);
            }
            if (StringUtils.isBlank(this.token) || !valid) {
                return null;
            }
            return this.token;
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
    }
}
