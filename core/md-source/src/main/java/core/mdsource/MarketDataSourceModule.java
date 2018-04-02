package core.mdsource;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class MarketDataSourceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MarketDataSourceFactory.class).to(GdaxSourceFactory.class).in(Singleton.class);
    }
}
