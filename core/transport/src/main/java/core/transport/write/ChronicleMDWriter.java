package core.transport.write;

import com.google.inject.Inject;
import common.data.marketdata.L3Quote;
import common.messaging.MDWriter;
import common.transport.SerialWriter;
import common.transport.SerialWriterFactory;

import java.nio.ByteBuffer;

/**
 * A chronicle-writing implementation of the MDWriter
 */
final class ChronicleMDWriter implements MDWriter {

    private final static String DATA_PATH = "data/transport/mds";

    /**
     * The default size of our intermediate message buffer
     */
    private static final int LARGE_CAPACITY = 512;

    /**
     * Every message we write will be first packaged into this buffer which then gets passed to the {@link #writer}
     */
    private final ByteBuffer buffer;

    /**
     * Write every message into this writer. Most like some kind of IPC component, e.g. Chronicle
     */
    private final SerialWriter writer;

    @Inject
    ChronicleMDWriter(SerialWriterFactory factory) {
        buffer = ByteBuffer.allocate(LARGE_CAPACITY);
        writer = factory.create(DATA_PATH);
    }

    @Override
    public void l3Quote(L3Quote quote) {
        buffer.clear();
        buffer.putShort(0, quote.id());
        buffer.putDouble(L3Quote.sizeI(), quote.size());
        buffer.putDouble(L3Quote.priceI(), quote.price());
        writer.write(buffer);
    }
}
