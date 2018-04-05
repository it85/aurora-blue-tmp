package core.transport.read;

import com.google.inject.Singleton;
import common.messaging.MDDispatcher;
import common.transport.BufferHandler;
import common.transport.DomainReader;

public class MDReaderTransportModule extends ReaderTransportModule {

    @Override
    protected void configure() {
        super.configure();

        bind(MDDispatcher.class).to(MDDispatcherImpl.class).in(Singleton.class);
        bind(DomainReader.class).to(MDReader.class).in(Singleton.class);
        bind(BufferHandler.class).to(MDBufferHandler.class).in(Singleton.class);
    }

}
