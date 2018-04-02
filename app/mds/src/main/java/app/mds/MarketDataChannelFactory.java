package app.mds;

import com.google.inject.Inject;
import common.data.marketdata.MarketDataSource;
import common.network.SocketManager;
import common.transport.SerialWriter;
import common.transport.ipc.SerialWriterFactory;

/**
 * A simple factory for creating a new MarketDataChannel instance
 */

final class MarketDataChannelFactory {

    private final SocketManager manager;
    private final SerialWriter writer;

    @Inject
    MarketDataChannelFactory(SocketManager manager, SerialWriterFactory factory) {
        this.manager = manager;
        this.writer = factory.create("transport/mds");
    }

    MarketDataChannel create(MarketDataSource source) {
        return new MarketDataChannel(manager, writer, source);
    }
}
