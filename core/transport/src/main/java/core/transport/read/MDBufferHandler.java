package core.transport.read;

import com.google.inject.Inject;
import common.data.marketdata.L3Quote;
import common.data.marketdata.MDHandler;
import common.messaging.marketdata.L3QuoteMessage;
import common.transport.BufferHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;

final class MDBufferHandler implements BufferHandler {

    private static final Logger LOG = LogManager.getLogger(MDBufferHandler.class);

    private final MDHandler handler;

    /**
     * Market data object containers
     */
    private final L3Quote l3Quote = new L3Quote();

    @Inject
    MDBufferHandler(MDHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(ByteBuffer buffer) {
        int id = buffer.getShort(0);    // TODO: encapsulate better the extraction of the message ID

        switch (id) {
            case L3QuoteMessage.ID:
                L3QuoteMessage.parse(l3Quote, buffer);
                handler.handle(l3Quote);
                break;
            default:
                LOG.error("No handler found for message Id {}", id);
        }
    }
}
