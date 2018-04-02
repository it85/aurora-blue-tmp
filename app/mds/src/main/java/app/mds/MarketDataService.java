package app.mds;

import com.google.inject.Inject;
import common.data.marketdata.MarketDataSource;
import common.data.marketdata.MarketDataSource.Type;
import core.mdsource.MarketDataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Encapsulates multiple market data channels and normalizes all of their data by writing/persisted it some shared
 * transport layer.
 */

public class MarketDataService implements Runnable {

    private static final Logger LOG = LogManager.getLogger(MarketDataChannel.class);

    //    private final MarketDataChannel level1;   // TODO: implement L1 at some point
    private final MarketDataChannel level2;
    private final MarketDataChannel level3;

    private boolean l2Enabled;
    private boolean l3Enabled;

    @Inject
    public MarketDataService(MarketDataSourceFactory sourceFactory, MarketDataChannelFactory channelFactory) {
        MarketDataSource l2Source = sourceFactory.create(Type.L2);
        MarketDataSource l3Source = sourceFactory.create(Type.L3);

        level2 = channelFactory.create(l2Source);
        level3 = channelFactory.create(l3Source);

        l2Enabled = false;
        l3Enabled = false;
    }

    void enableL2() {
        l2Enabled = true;
    }

    void enableL3() {
        l3Enabled = true;
    }

    @Override
    public void run() {
        if (l2Enabled) {
            LOG.debug("Starting Level 2 market data channel");
            new Thread(level2).start();
        }

        if (l3Enabled) {
            LOG.debug("Starting Level 3 market data channel");
            new Thread(level3).start();
        }
    }
}
