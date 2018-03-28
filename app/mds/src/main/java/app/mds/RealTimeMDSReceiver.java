package app.mds;

import com.google.inject.Inject;
import common.data.messaging.Serializable;
import common.data.marketdata.MarketDataSource;
import common.data.messaging.MessageHandler;
import common.network.SocketManager;
import common.transport.SerialWriter;
import common.transport.ipc.SerialWriterFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class serves as an abstraction to facilitate real-time communication with a market data socket and normalize
 * inbound packets into an internal market data format.
 * <p>
 * 1. open a socket connection using the SocketManager
 * 2. receive real-time market data
 * 3. normalize into internal data structure protocol
 * 4. persists into a buffer
 */
final class RealTimeMDSReceiver<T extends Serializable> implements Runnable, MessageHandler {

    private static final Logger LOG = LogManager.getLogger(RealTimeMDSReceiver.class);

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
    public RealTimeMDSReceiver(SocketManager socketManager,
                               SerialWriterFactory serialWriterFactory,
                               MarketDataSource source) {
        this.socketManager = socketManager;
        this.source = source;
        this.writer = serialWriterFactory.create("transport/mds");    // TODO: abstract out this file path concern..
    }

    @Override
    public void run() {
        socketManager.begin(this, source);
    }

    @Override
    public void onMessage(String message) {
        Serializable data = source.convert(message);
        writer.write(data);

        if (LOG.isTraceEnabled()) {
            LOG.trace(data);
        }
    }
}
