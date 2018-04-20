package core.transport.write;

import com.google.inject.Inject;
import common.data.marketdata.Book;
import common.data.marketdata.L3Quote;
import common.messaging.marketdata.L3QuoteMessage;
import common.messaging.marketdata.MDWriter;
import common.transport.SerialWriter;
import common.transport.SerialWriterFactory;

/**
 * A chronicle-writing implementation of the MDWriter
 */
final class ChronicleMDWriter implements MDWriter {

    private final static String DATA_PATH = "data/transport/mds";

    /**
     * Write every message into this writer. Most like some kind of IPC component, e.g. Chronicle
     */
    private final SerialWriter writer;

    @Inject
    ChronicleMDWriter(SerialWriterFactory factory) {
        writer = factory.create(DATA_PATH);
    }

    @Override
    public void l3Quote(L3Quote quote) {
        writer.write(L3QuoteMessage.pack(quote));
    }

    @Override
    public void book(Book book) {
        // TODO:  
    }
}
