package app.analytics;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import common.data.marketdata.L3Handler;
import common.data.marketdata.MDHandler;
import core.transport.read.MDReaderTransportModule;

public final class AnalyticsModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new MDReaderTransportModule());

        bind(MDHandler.class).to(AnalyticsMDHandler.class).in(Singleton.class);
        bind(L3Handler.class).to(L3Analytics.class).in(Singleton.class);
    }
}
