package app.mds;

import com.google.inject.Inject;
import common.data.marketdata.MarketDataSource;
import common.data.messaging.MessageHandler;
import common.network.SocketManager;
import common.network.WssEndpoint;
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

    /**
     * The socket endpoint we're trying to connect to acquire market data
     */
    private final WssEndpoint endpoint;

    @Inject
    public RealTimeMDSEngine(SocketManager socketManager, MarketDataSource<T> source, WssEndpoint endpoint) {
        this.socketManager = socketManager;
        this.source = source;
        this.endpoint = endpoint;
    }

    @Override
    public void run() {
        socketManager.begin(this, endpoint);
    }

    @Override
    public void onMessage(String message) {
        T quote = source.convert(message);
        LOG.debug(quote);
    }
}
