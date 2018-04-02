package app.mds;

import com.google.inject.Guice;
import com.google.inject.Injector;
import common.data.marketdata.MarketDataSource.Type;
import core.mdsource.MarketDataSourceFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

// TODO: Fix this unit test because Chronicle is complaining about multi-threaded access
public class MarketDataChannelTest {

    private MarketDataChannel level2;
    private MarketDataChannel level3;

    @Before
    public void setup() {
        Injector injector = Guice.createInjector(new MDSModule());
        MarketDataSourceFactory sourceFactory = injector.getInstance(MarketDataSourceFactory.class);

        level2 = injector.getInstance(MarketDataChannelFactory.class).create(sourceFactory.create(Type.L2));
        level3 = injector.getInstance(MarketDataChannelFactory.class).create(sourceFactory.create(Type.L3));
    }

    @Ignore
    @Test
    public void canConnectToLevel2Test() throws InterruptedException {
        level2.run();
        Thread.sleep(1000);
    }

    @Ignore
    @Test
    public void canConnectToLevel3Test() throws InterruptedException {
        level3.run();
        Thread.sleep(1000);
    }
}
