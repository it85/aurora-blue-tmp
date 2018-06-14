package core.transport.read;

import com.google.inject.Inject;
import common.data.marketdata.MDHandler;
import common.messaging.marketdata.BookMessage;
import common.messaging.marketdata.L3QuoteMessage;
import common.transport.BufferHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;

final class MDBufferHandler implements BufferHandler {

    private static final Logger LOG = LogManager.getLogger(MDBufferHandler.class);

    /**
     * Market data object containers
     */
    private final L3QuoteMessage l3Quote = new L3QuoteMessage();
    private final BookMessage book = new BookMessage();

    private final MDHandler handler;

    @Inject
    MDBufferHandler(MDHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(ByteBuffer buffer) {
        short id = buffer.array()[1];    // TODO: encapsulate better the extraction of the message ID

        switch (id) {
            case L3QuoteMessage.ID:
                l3Quote.from(buffer);
                handler.handle(l3Quote);
                break;
            case BookMessage.ID:
                book.from(buffer);
                handler.handle(book);
            default:
                LOG.error("No handler found for message Id {}", id);
        }
    }
}
