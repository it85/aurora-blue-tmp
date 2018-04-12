package app.analytics;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import common.data.marketdata.L3Handler;
import common.data.marketdata.MDHandler;
import common.util.time.TimeModule;
import core.transport.read.MDReaderTransportModule;

public final class AnalyticsModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new MDReaderTransportModule());
        install(new TimeModule());

        install(new FactoryModuleBuilder()
                .implement(Turnover.class, Turnover.class)
                .build(TurnoverFactory.class));

        bind(MDHandler.class).to(AnalyticsMDHandler.class).in(Singleton.class);
        bind(L3Handler.class).to(L3Analytics.class).in(Singleton.class);
        bind(TurnoverCalculator.class).in(Singleton.class);
    }
}
