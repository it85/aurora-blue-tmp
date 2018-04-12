package app.mds;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import common.collection.SharedQueueBuffer;
import common.data.marketdata.MarketDataSource;
import common.data.type.Serializable;
import common.messaging.MessageHandler;
import common.network.SocketManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A single market data channel which supports a specific form of market data, e.g. L1, L2, or L3. Each channel spawns its
 * own thread for performing its work.
 * <p>
 * It's imperative that the {@link #init()} function is called before anything else since all channels hit the same
 * socket endpoint which means requests to the socket must be made/handled serially.
 * <p>
 * <p>
 * High Level Procedure
 * 1. open a socket connection using the SocketManager
 * 2. receive real-time market data
 * 3. normalize into internal data structure protocol
 * 4. persists into a buffer
 */
final class MDChannel implements Runnable, MessageHandler {

    private static final Logger LOG = LogManager.getLogger(MDChannel.class);

    /**
     * Manages the connection to the market data source endpoint
     */
    private final SocketManager socketManager;

    /**
     * An abstraction of the market data source we're talking to
     */
    private final MarketDataSource source;

    /**
     * Market data gets persisted into this queue
     */
    private final SharedQueueBuffer<Serializable> buffer;

    @Inject
    MDChannel(SocketManager socketManager,
              SharedQueueBuffer<Serializable> buffer,
              @Assisted MarketDataSource source) {
        this.socketManager = socketManager;
        this.source = source;
        this.buffer = buffer;

        init();
    }

    private void init() {
        socketManager.init(this, source);
    }

    @Override
    public void run() {
        socketManager.begin();
    }

    @Override
    public void onMessage(String message) {
        Serializable data = source.translate(message);
        buffer.add(data);

        if (LOG.isTraceEnabled()) {
            LOG.trace(data);
        }
    }
}
