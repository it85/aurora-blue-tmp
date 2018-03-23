package app.mds.realtime;

import com.google.inject.Inject;
import common.data.marketdata.L3Quote;
import common.data.marketdata.MarketDataSource;
import common.messaging.MessageHandler;
import network.SocketManager;

/**
 * Opens a socket connection using the SocketManager, receives real-time market data, and persists into a buffer.
 */
final class RealTimeMDSEngine implements Runnable, MessageHandler {

    /**
     * Manages the connection to the market data source endpoint
     */
    private final SocketManager socketManager;

    /**
     * An abstraction of the market data source we're talking to
     */
    private final MarketDataSource source;

    @Inject
    public RealTimeMDSEngine(SocketManager socketManager, MarketDataSource source) {
        this.socketManager = socketManager;
        this.source = source;
    }

    @Override
    public void run() {
        socketManager.begin(this);
    }

    @Override
    public void onMessage(String message) {
        L3Quote quote = source.convert(message);
    }
}
