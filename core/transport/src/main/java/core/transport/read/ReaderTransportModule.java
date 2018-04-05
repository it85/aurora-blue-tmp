package core.transport.read;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import common.transport.RawReader;
import common.transport.SerialReaderFactory;

class ReaderTransportModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(RawReader.class, ChronicleReader.class)
                .build(SerialReaderFactory.class));

        bind(GenericReader.class).in(Singleton.class);
    }
}
