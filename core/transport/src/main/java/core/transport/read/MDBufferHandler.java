package core.transport.read;

import com.google.inject.Inject;
import common.data.marketdata.L3Quote;
import common.messaging.MDDispatcher;
import common.transport.BufferHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;

final class MDBufferHandler implements BufferHandler {

    private static final Logger LOG = LogManager.getLogger(MDBufferHandler.class);

    private final MDDispatcher reader;

    @Inject
    MDBufferHandler(MDDispatcher reader) {
        this.reader = reader;
    }

    @Override
    public void handle(ByteBuffer buffer) {
        int id = buffer.getInt(0);

        switch (id) {
            case L3Quote.ID:
                reader.l3Quote(buffer);
                break;
            default:
                LOG.warn("No handler found for message Id {}", id);
        }
    }
}
