package app.analytics;

import com.google.inject.Inject;
import common.analytics.turnover.Turnover;
import common.analytics.turnover.TurnoverFactory;
import common.data.marketdata.L3Handler;
import common.data.marketdata.L3Quote;

/**
 * Calculates various turnover analytics
 * <p>
 * For Day 1, we're going to track a very simple statistic: How often an L3 event happens. We do so by maintaining a
 * moving average of elapsed times since the last event was seen. This similar in concept to spread-turnover.
 */
final class TurnoverCalculator implements L3Handler {

    private final Turnover matchTurnover;
    private final Turnover openTurnover;
    private final Turnover receivedTurnover;

    @Inject
    TurnoverCalculator(TurnoverFactory factory) {
        matchTurnover = factory.create(5);
        openTurnover = factory.create(200);
        receivedTurnover = factory.create(200);
    }

    @Override
    public void handle(L3Quote quote) {
        switch (quote.type()) {
            case MATCH:
                matchTurnover.newEvent();
                break;
            case OPEN:
                openTurnover.newEvent();
                break;
            case RECEIVED:
                receivedTurnover.newEvent();
                break;
            default:
                // do nothing
        }
    }
}
