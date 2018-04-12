package app.mds;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import common.data.type.Serializable;
import core.collection.SingletonCollection;
import core.mdsource.MarketDataSourceModule;
import core.network.NetworkModule;
import core.transport.write.MDWriterTransportModule;

public class MDSModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new NetworkModule());
        install(new MDWriterTransportModule());
        install(new MarketDataSourceModule());
        install(new SingletonCollection<Serializable>());

        install(new FactoryModuleBuilder()
                .implement(MDChannelImpl.class, MDChannelImpl.class)
                .build(MDChannelFactory.class));

        bind(MDService.class).in(Singleton.class);
        bind(MDS.class).in(Singleton.class);
    }
}
