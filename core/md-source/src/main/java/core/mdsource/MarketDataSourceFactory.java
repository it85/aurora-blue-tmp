package core.mdsource;

import common.data.marketdata.MarketDataSource;
import common.data.marketdata.MarketDataSource.Type;

public interface MarketDataSourceFactory {

    MarketDataSource create(Type type);

}
