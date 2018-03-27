package app.mds;

import app.gdax.L2Gdax;
import app.gdax.L3Gdax;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import common.data.marketdata.Book;
import common.data.marketdata.L3Quote;
import common.data.marketdata.MarketDataSource;
import network.NetworkModule;
import network.WssEndpoint;

public class MDSModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new NetworkModule());

        bind(WssEndpoint.class).to(L2Gdax.class).in(Singleton.class);
        bind(new TypeLiteral<MarketDataSource<Book>>(){}).to(L2Gdax.class);

        bind(WssEndpoint.class).to(L3Gdax.class).in(Singleton.class);
        bind(new TypeLiteral<MarketDataSource<L3Quote>>(){}).to(L3Gdax.class);
    }
}
