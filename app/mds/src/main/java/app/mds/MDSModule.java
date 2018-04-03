package app.mds;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import common.data.type.Serializable;
import core.collection.SingletonCollection;
import core.mdsource.MarketDataSourceModule;
import core.network.NetworkModule;
import core.transport.TransportModule;

public class MDSModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new NetworkModule());
        install(new TransportModule());
        install(new MarketDataSourceModule());
        install(new SingletonCollection<Serializable>());

        bind(MDChannelFactory.class).in(Singleton.class);
        bind(MDService.class).in(Singleton.class);
        bind(MDS.class).in(Singleton.class);
    }
}
