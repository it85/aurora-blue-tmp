package app.mds;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import core.mdsource.MarketDataSourceModule;
import core.network.NetworkModule;
import core.transport.TransportModule;

public class MDSModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new NetworkModule());
        install(new TransportModule());
        install(new MarketDataSourceModule());

        bind(MarketDataChannelFactory.class).in(Singleton.class);
    }
}
