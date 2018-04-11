package app.analytics;

import common.data.marketdata.L3Quote;
import common.messaging.MDHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

final class AnalyticsMDHandler implements MDHandler {

    private static final Logger LOG = LogManager.getLogger(AnalyticsMDHandler.class);

    @Override
    public void handle(L3Quote quote) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Handling {}", quote);
        }
    }
}
