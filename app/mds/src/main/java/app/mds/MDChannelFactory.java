package app.mds;

import common.data.marketdata.MarketDataSource;

/**
 * There is no explicit implementation of this class -- guice handles this for us through an implicit binding (see
 * Assisted injection and how this interface is bound in the module).
 */
public interface MDChannelFactory {

    MDChannelImpl create(MarketDataSource source);

}
