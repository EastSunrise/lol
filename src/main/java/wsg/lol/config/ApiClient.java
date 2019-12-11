package wsg.lol.config;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.Uninterruptibles;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.time.DateUtils;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import wsg.lol.common.base.AppException;
import wsg.lol.common.constant.ErrorCodeConst;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
            this.apis.add(new RGApi(token, rate));
        }
    }

    private static class RGApi implements Comparable<RGApi> {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        private String username;
        private String token;
        private Date expires;
        private double maxPermits;
        private double stableIntervalMicros;
        private double storedPermits;
        private long nextFreeTicket;
        @MonotonicNonNull
        private volatile Object mutexDoNotUseDirectly;

        RGApi(Token token, double permitsPerSecond) {
            this.username = token.username;
            this.token = token.key;
            this.expires = parseDate(token.expires);
            synchronized (mutex()) {
                doSetRate(permitsPerSecond, stopwatch.elapsed(MICROSECONDS));
            }
        }

        private static Date parseDate(String str) {
            String[] parts = str.split(",");
            parts[1] = parts[1].substring(0, parts[1].length() - 2);
            try {
                return DateUtils.parseDate(org.apache.commons.lang3.StringUtils.join(parts, ","), Locale.ENGLISH, "EEE, MMM dd, yyyy @ h:mmaa (Z)");
            } catch (ParseException e) {
                e.printStackTrace();
                throw new AppException(ErrorCodeConst.SYSTEM_ERROR, e);
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
            return (StringUtils.isEmpty(this.token) || this.expires.compareTo(new Date()) < 0) ? null : this.token;
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
            Date now = new Date();
            if (this.expires.compareTo(now) > 0 && api.expires.compareTo(now) > 0) {
                return Long.compare(this.nextFreeTicket, api.nextFreeTicket);
            }
            return this.expires.compareTo(api.expires);
        }
    }

    @Setter
    private static class Token {
        private String username;
        private String key;
        private String expires;
    }
}
