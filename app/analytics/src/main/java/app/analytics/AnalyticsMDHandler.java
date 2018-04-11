package app.analytics;

import com.google.inject.Inject;
import common.data.marketdata.L3Handler;
import common.data.marketdata.L3Quote;
import common.data.marketdata.MDHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

final class AnalyticsMDHandler implements MDHandler {

    private static final Logger LOG = LogManager.getLogger(AnalyticsMDHandler.class);

    private final L3Handler l3Handler;

    @Inject
    AnalyticsMDHandler(L3Handler l3Handler) {
        this.l3Handler = l3Handler;
    }

    @Override
    public void handle(L3Quote quote) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Handling {}", quote);
        }
        l3Handler.handle(quote);
    }
}
