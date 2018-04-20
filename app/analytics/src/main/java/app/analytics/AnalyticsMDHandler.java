package app.analytics;

import com.google.inject.Inject;
import common.data.marketdata.Book;
import common.data.marketdata.BookHandler;
import common.data.marketdata.L3Handler;
import common.data.marketdata.L3Quote;
import common.data.marketdata.MDHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

final class AnalyticsMDHandler implements MDHandler {

    private static final Logger LOG = LogManager.getLogger(AnalyticsMDHandler.class);

    private final L3Handler l3Handler;
    private final BookHandler bookHandler;

    @Inject
    AnalyticsMDHandler(L3Handler l3Handler, BookHandler bookHandler) {
        this.l3Handler = l3Handler;
        this.bookHandler = bookHandler;
    }

    @Override
    public void handle(L3Quote quote) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Handling {}", quote);
        }
        l3Handler.handle(quote);
    }

    @Override
    public void handle(Book book) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Handling {}", book);
        }
        bookHandler.handle(book);
    }
}
