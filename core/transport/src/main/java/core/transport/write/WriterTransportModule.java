package core.transport.write;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import common.transport.SerialWriter;
import common.transport.SerialWriterFactory;

class WriterTransportModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(SerialWriter.class, ChronicleWriter.class)
                .build(SerialWriterFactory.class));
    }
}
