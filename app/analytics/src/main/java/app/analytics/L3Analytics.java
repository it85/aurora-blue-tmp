package app.analytics;

import com.google.inject.Inject;
import common.data.marketdata.L3Handler;
import common.data.marketdata.L3Quote;

/**
 * Tracks various analytics based on a level 3 quote feed
 */
final class L3Analytics implements L3Handler {

    /**
     * Calculates various turnover-related analytics
     */
    private final TurnoverCalculator turnover;

    @Inject
    L3Analytics(TurnoverCalculator turnover) {
        this.turnover = turnover;
    }

    /**
     * Chain together a series of analytics calculations
     */
    @Override
    public void handle(L3Quote quote) {
        turnover.handle(quote);
    }
}
