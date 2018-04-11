package app.analytics;

import common.data.marketdata.L3Handler;
import common.data.marketdata.L3Quote;

/**
 * Tracks various analytics based on a level 3 quote feed
 */
final class L3Analytics implements L3Handler {

    private final TurnoverCalculator turnover = new TurnoverCalculator();

    /**
     * Chain together a series of analytics calculations
     */
    @Override
    public void handle(L3Quote quote) {
        turnover.handle(quote);
    }
}
