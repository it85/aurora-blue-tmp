package app.analytics;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import common.collection.BoundedArrayDeque;
import common.collection.BoundedQueue;
import common.util.MathUtil;
import common.util.time.Time;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Tracks a specific turnover. What metric this class tracks is dependent on how the caller is using it.
 */
final class Turnover {

    private static final Logger LOG = LogManager.getLogger(Turnover.class);

    /**
     * Store the elapsed from when the last event was seen
     */
    private final BoundedQueue<Long> elapsedTimes;
    private final Time time;

    /**
     * The last time a match occurred
     */
    private long lastEventTime;

    @Inject
    Turnover(Time time,
             @Assisted int period) {
        this.time = time;
        elapsedTimes = new BoundedArrayDeque<>(period);
    }

    /**
     * Invoke this method when a new event is seen
     */
    void newEvent() {
        if (LOG.isTraceEnabled()) {
            LOG.trace("New event");
        }

        if (lastEventTime == 0) {
            lastEventTime = time.nowMillis();
        }

        long now = time.nowMillis();
        elapsedTimes.add(now - lastEventTime);
        lastEventTime = now;
    }

    /**
     * @return the current turnover expressed as an average of all elapsed times seen thus far
     */
    double turnover() {
        return MathUtil.average(elapsedTimes);
    }
}
