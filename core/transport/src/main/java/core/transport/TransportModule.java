package core.transport;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import common.transport.SerialWriter;
import common.transport.ipc.SerialWriterFactory;

public class TransportModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(SerialWriter.class, ChronicleWriter.class)
                .build(SerialWriterFactory.class));
    }
}
