package core.transport.read;

import com.google.inject.Inject;
import common.transport.BufferHandler;
import common.transport.DomainReader;
import common.transport.RawReader;
import common.transport.SerialReaderFactory;

final class MDReader implements DomainReader {

    private final BufferHandler handler;
    private final RawReader reader;

    @Inject
    MDReader(SerialReaderFactory factory,
                    BufferHandler handler) {
        this.handler = handler;
        this.reader = factory.create("data/transport/mds"); // TODO: this path should be shared
    }

    @Override
    public BufferHandler handler() {
        return handler;
    }

    @Override
    public RawReader reader() {
        return reader;
    }
}
