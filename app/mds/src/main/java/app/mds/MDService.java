package app.mds;

import com.google.inject.Inject;
import common.data.marketdata.MarketDataSource;
import common.data.marketdata.MarketDataSource.Type;
import core.mdsource.MarketDataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Encapsulates, and initializes, multiple market data channels. This class is responsible for starting up enabled
 * channels.
 */

final class MDService {

    private static final Logger LOG = LogManager.getLogger(MDChannel.class);

    //    private final MDChannel level1;   // TODO: implement L1 at some point
    private final MDChannel level2;
    private final MDChannel level3;

    private boolean l2Enabled;
    private boolean l3Enabled;

    @Inject
    MDService(MarketDataSourceFactory sourceFactory, MDChannelFactory channelFactory) {
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

    public void start() {
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
