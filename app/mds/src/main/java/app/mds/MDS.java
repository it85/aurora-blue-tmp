package app.mds;

import com.google.inject.Inject;
import common.collection.SharedQueueBuffer;
import common.data.type.Serializable;
import common.transport.SerialWriter;
import common.transport.ipc.SerialWriterFactory;
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
    private final SerialWriter writer;
    private final SharedQueueBuffer<Serializable> buffer;

    @Inject
    public MDS(MDService service, SerialWriterFactory factory, SharedQueueBuffer<Serializable> buffer) {
        this.service = service;
        this.buffer = buffer;
        this.writer = factory.create("data/transport/mds");

        // TODO: abstract out the enabling of different channels out of this class
        service.enableL2();
        service.enableL3();
    }

    private void init() {
        LOG.debug("Starting up market data service");
        service.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void poll() {
        // TODO: improve busy spin policy
        while (true) {
            if (buffer.peek() != null) {
                if (LOG.isTraceEnabled()) {
                    LOG.trace(buffer.peek());
                }

                writer.write(buffer.poll());
            }
        }
    }

    @Override
    public void run() {
        init();
        poll();
    }
}
