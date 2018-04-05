package core.transport.read;

import com.google.inject.Singleton;
import common.messaging.MDHandler;
import common.transport.BufferHandler;
import common.transport.DomainReader;

public class MDReaderTransportModule extends ReaderTransportModule {

    @Override
    protected void configure() {
        super.configure();

        bind(MDHandler.class).to(MDHandlerImpl.class).in(Singleton.class);
        bind(DomainReader.class).to(MDReader.class).in(Singleton.class);
        bind(BufferHandler.class).to(MDBufferHandler.class).in(Singleton.class);
    }

}
