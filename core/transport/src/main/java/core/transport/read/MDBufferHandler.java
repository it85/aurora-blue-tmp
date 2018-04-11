package core.transport.read;

import com.google.inject.Inject;
import common.data.marketdata.L3Quote;
import common.messaging.L3QuoteMessage;
import common.messaging.MDHandler;
import common.transport.BufferHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;

final class MDBufferHandler implements BufferHandler {

    private static final Logger LOG = LogManager.getLogger(MDBufferHandler.class);

    private final MDHandler handler;

    /**
     * Market data containers
     */
    private final L3Quote l3Quote = new L3Quote();

    @Inject
    MDBufferHandler(MDHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(ByteBuffer buffer) {
        int id = buffer.getShort(0);

        switch (id) {
            case L3QuoteMessage.ID:
                L3QuoteMessage.parse(l3Quote, buffer);
                handler.handle(l3Quote);
                break;
            default:
                LOG.warn("No handler found for message Id {}", id);
        }
    }
}
