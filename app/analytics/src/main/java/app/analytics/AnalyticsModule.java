package app.analytics;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import common.messaging.MDHandler;
import core.transport.read.MDReaderTransportModule;

public final class AnalyticsModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new MDReaderTransportModule());

        bind(MDHandler.class).to(AnalyticsMDHandler.class).in(Singleton.class);
    }
}
