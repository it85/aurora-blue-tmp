package app.mds;

import com.google.inject.Inject;
import common.data.marketdata.MarketDataSource;
import common.data.messaging.MessageHandler;
import common.data.type.Serializable;
import common.network.SocketManager;
import common.transport.SerialWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A single market data channel which supports a specific form of market data, e.g. L1, L2, or L3. Each channel spawns its
 * own thread for performing its work.
 * <p>
 * High Level Procedure
 * 1. open a socket connection using the SocketManager
 * 2. receive real-time market data
 * 3. normalize into internal data structure protocol
 * 4. persists into a buffer
 */
// TODO: The socket invokes onMessage potentially via different threads which is problematic for Chronicle: possible solution is to commit the String message to a shared queue which this class is continuously polling on our native thread to then write to C
final class MarketDataChannel implements Runnable, MessageHandler {

    private static final Logger LOG = LogManager.getLogger(MarketDataChannel.class);

    /**
     * Manages the connection to the market data source endpoint
     */
    private final SocketManager socketManager;

    /**
     * An abstraction of the market data source we're talking to
     */
    private final MarketDataSource source;
    private final SerialWriter writer;

    @Inject
    MarketDataChannel(SocketManager socketManager,
                             SerialWriter serialWriter,
                             MarketDataSource source) {
        this.socketManager = socketManager;
        this.source = source;
        this.writer = serialWriter;
    }

    @Override
    public void run() {
        socketManager.begin(this, source);
    }

    @Override
    public void onMessage(String message) {
        Serializable data = source.convert(message);
        System.out.println(Thread.currentThread().getName());
        writer.write(data);

        if (LOG.isTraceEnabled()) {
            LOG.trace(data);
        }
    }
}
