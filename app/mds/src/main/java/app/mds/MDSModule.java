package app.mds;

import app.gdax.Gdax;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import common.data.marketdata.MarketDataSource;
import network.NetworkModule;
import network.WssEndpoint;

public class MDSModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new NetworkModule());

        bind(WssEndpoint.class).to(Gdax.class).in(Singleton.class);
        bind(MarketDataSource.class).to(Gdax.class).in(Singleton.class);
    }
}
