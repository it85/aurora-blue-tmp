package app.mds;

import com.google.inject.Inject;
import common.collection.SharedQueueBuffer;
import common.data.marketdata.MarketDataSource;
import common.data.type.Serializable;
import common.network.SocketManager;

/**
 * A simple factory for creating a new MDChannel instance
 */

final class MDChannelFactory {

    private final SocketManager manager;
    private final SharedQueueBuffer<Serializable> buffer;

    @Inject
    MDChannelFactory(SocketManager manager, SharedQueueBuffer<Serializable> buffer) {
        this.manager = manager;
        this.buffer = buffer;
    }

    MDChannel create(MarketDataSource source) {
        return new MDChannel(manager, buffer, source);
    }
}
