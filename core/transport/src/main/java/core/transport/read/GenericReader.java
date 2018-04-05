package core.transport.read;

import com.google.inject.Inject;
import common.transport.BufferHandler;
import common.transport.DomainReader;
import common.transport.RawReader;

public class GenericReader implements Runnable {

    private final RawReader reader;
    private final BufferHandler handler;

    @Inject
    GenericReader(DomainReader domainReader) {
        this.reader = domainReader.reader();
        this.handler = domainReader.handler();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            reader.poll(handler);
        }
    }
}
