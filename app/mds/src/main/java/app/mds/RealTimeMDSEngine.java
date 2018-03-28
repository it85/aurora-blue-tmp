package app.mds;

import com.google.inject.Inject;
import common.data.marketdata.MarketDataSource;
import common.data.messaging.MessageHandler;
import common.network.SocketManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class serves as an abstraction to facilitate real-time communication with a market data socket and normalize
 * inbound packets into an internal market data format.
 *
 * 1. open a socket connection using the SocketManager
 * 2. receive real-time market data
 * 3. normalize into internal data structure protocol
 * 4. persists into a buffer
 */
final class RealTimeMDSEngine<T> implements Runnable, MessageHandler {

    private static final Logger LOG = LogManager.getLogger(RealTimeMDSEngine.class);

    /**
     * Manages the connection to the market data source endpoint
     */
    private final SocketManager socketManager;

    /**
     * An abstraction of the market data source we're talking to
     */
    private final MarketDataSource<T> source;

    @Inject
    public RealTimeMDSEngine(SocketManager socketManager, MarketDataSource<T> source) {
        this.socketManager = socketManager;
        this.source = source;
    }

    @Override
    public void run() {
        socketManager.begin(this, source);
    }

    @Override
    public void onMessage(String message) {
        T quote = source.convert(message);
        LOG.debug(quote);
    }
}
