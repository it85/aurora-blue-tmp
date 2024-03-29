package app.mds;

import com.google.inject.Guice;
import com.google.inject.Injector;
import common.data.marketdata.MarketDataSource.Type;
import core.mdsource.MarketDataSourceFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MDChannelTest {

    private MDChannel level2;
    private MDChannel level3;

    @Before
    public void setup() {
        Injector injector = Guice.createInjector(new MDSModule());
        MarketDataSourceFactory sourceFactory = injector.getInstance(MarketDataSourceFactory.class);

        level2 = injector.getInstance(MDChannelFactory.class).create(sourceFactory.create(Type.L2));
        level3 = injector.getInstance(MDChannelFactory.class).create(sourceFactory.create(Type.L3));
    }

    @Test
    public void canConnectToLevel2Test() throws InterruptedException {
        level2.run();
        Thread.sleep(2000);
    }

    @Ignore
    @Test
    public void canConnectToLevel3Test() throws InterruptedException {
        level3.run();
        Thread.sleep(2000);
    }
}
