package app.mds;

import app.gdax.L2Gdax;
import app.gdax.L3Gdax;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import common.data.marketdata.Book;
import common.data.marketdata.L3Quote;
import common.data.marketdata.MarketDataSource;
import core.network.NetworkModule;
import core.transport.TransportModule;

public class MDSModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new NetworkModule());
        install(new TransportModule());

        bind(new TypeLiteral<MarketDataSource<Book>>(){}).to(L2Gdax.class);
        bind(new TypeLiteral<MarketDataSource<L3Quote>>(){}).to(L3Gdax.class);
    }
}
