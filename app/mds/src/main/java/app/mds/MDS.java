package app.mds;

import com.google.inject.Inject;
import common.collection.SharedQueueBuffer;
import common.data.marketdata.Book;
import common.data.marketdata.L3Quote;
import common.data.type.Serializable;
import common.messaging.marketdata.MDWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class initializes the Market Data service {@link MDService} which is writing market data into a shared buffer.
 * This shared buffer is consumed by this Gateway class which then commits each buffer object to the
 * {@link common.transport.SerialWriter}.
 */
public final class MDS implements Runnable {

    private static final Logger LOG = LogManager.getLogger(MDS.class);

    private final MDService service;
    private final MDWriter writer;
    private final SharedQueueBuffer<Serializable> buffer;

    @Inject
    public MDS(MDService service, MDWriter writer, SharedQueueBuffer<Serializable> buffer) {
        this.service = service;
        this.buffer = buffer;
        this.writer = writer;

        // TODO: abstract out the enabling of different channels out of this class?
        service.enableL2();
        service.enableL3();
    }

    @Override
    public void run() {
        init();
        poll();
    }

    private void init() {
        LOG.debug("Starting up market data service");
        service.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void poll() {
        // TODO: improve busy spin policy?
        while (true) {
            if (buffer.peek() != null) {
                if (LOG.isTraceEnabled()) {
                    LOG.trace(buffer.peek());
                }
                write(buffer.poll());
            }
        }
    }

    // TODO: figure out a way to get rid of doing dirty casting -- might not be possible but need to try
    private void write(Serializable data) {
        if (data instanceof L3Quote) {
            writer.l3Quote((L3Quote) data);
        } else if (data instanceof Book) {
            writer.book((Book) data);
        }
    }
}
