package app.analytics;

import common.collection.BoundedArrayDeque;
import common.collection.BoundedQueue;
import common.data.marketdata.L3Handler;
import common.data.marketdata.L3Quote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Tracks various analytics based on a level 3 quote feed
 */
final class L3Analytics implements L3Handler {

    private static final Logger LOG = LogManager.getLogger(AnalyticsMDHandler.class);
    private static final int MATCH_PERIOD = 5;
    private static final int OPEN_PERIOD = 150;
    private static final int RECEIVED_PERIOD = 300;

    /**
     * The queues below store the elapsed from when the last event was seen.
     *
     * For instance, {@link #matchTimeDeltas} stores the time elapsed since the last match was observed.
     */
    private final BoundedQueue<Long> matchTimeDeltas = new BoundedArrayDeque<>(MATCH_PERIOD);
    private final BoundedQueue<Long> openTimeDeltas = new BoundedArrayDeque<>(OPEN_PERIOD);
    private final BoundedQueue<Long> receivedTimeDeltas = new BoundedArrayDeque<>(RECEIVED_PERIOD);

    /**
     * The last time a match occurred
     */
    private long lastMatchTime;

    /**
     * The last time an order was posted to the market
     */
    private long lastOpenTime;

    /**
     * The last time an order was received by the market, whether IOC or Day
     */
    private long lastReceiveTime;

    /**
     * For Day 1, we're going to track a very simple statistic: How often a match happens. We do so by maintaining a
     * moving average of elapsed times since the last match was seen. This is essentially a "match-turnover", similar
     * in concept spread-turnover.
     */
    @Override
    public void handle(L3Quote quote) {
        switch (quote.type()) {
            case MATCH:
                matchTurnover();
                break;
            case OPEN:
                openTurnover();
                break;
            case RECEIVED:
                receivedTurnover();
                break;
            default:
                // do nothing
        }
    }

    private void matchTurnover() {
        if (lastMatchTime == 0) {
            lastMatchTime = System.currentTimeMillis();
        }

        if (matchTimeDeltas.isFull()) {
            LOG.debug("Match-turnover average={}", getAverage(matchTimeDeltas));
        }

        long now = System.currentTimeMillis();
        matchTimeDeltas.add(now - lastMatchTime);
        lastMatchTime = now;
    }

    private void openTurnover(){
        if (lastOpenTime == 0) {
            lastOpenTime = System.currentTimeMillis();
        }

        if (openTimeDeltas.isFull()) {
            LOG.debug("Open-turnover average={}", getAverage(openTimeDeltas));
        }

        long now = System.currentTimeMillis();
        openTimeDeltas.add(now - lastOpenTime);
        lastOpenTime = now;
    }

    private void receivedTurnover(){
        if (lastReceiveTime == 0) {
            lastReceiveTime = System.currentTimeMillis();
        }

        if (receivedTimeDeltas.isFull()) {
            LOG.debug("Receive-turnover average={}", getAverage(receivedTimeDeltas));
        }

        long now = System.currentTimeMillis();
        receivedTimeDeltas.add(now - lastReceiveTime);
        lastReceiveTime = now;
    }

    private double getAverage(BoundedQueue<Long> queue) {
        double runningTotal = 0;
        for (int i = 0; i < queue.size(); i++) {
            runningTotal += queue.get(i);
        }
        return runningTotal / queue.size();
    }
}
