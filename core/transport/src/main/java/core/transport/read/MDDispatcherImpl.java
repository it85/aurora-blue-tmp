package core.transport.read;

import com.google.inject.Inject;
import common.data.marketdata.L3Quote;
import common.messaging.MDDispatcher;

import java.nio.ByteBuffer;

// TODO: come up with a better name than "...Impl"
final class MDDispatcherImpl implements MDDispatcher {

    private final L3Quote l3Quote;

    @Inject
    MDDispatcherImpl() {
        l3Quote = new L3Quote();
    }

    @Override
    public L3Quote l3Quote(ByteBuffer buffer) {
        return l3Quote;
    }
}
