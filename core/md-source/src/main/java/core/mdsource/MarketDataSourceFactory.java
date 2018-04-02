package core.mdsource;

import common.data.marketdata.MarketDataSource;
import common.data.marketdata.MarketDataSource.Type;

public class MarketDataSourceFactory {

    public MarketDataSource create(Type type) {
        switch (type) {
            case L1:
                throw new UnsupportedOperationException("L1 market data source not yet supported!");
            case L2:
                return new L2Gdax();
            case L3:
                return new L3Gdax();
            default:
                return null;
        }
    }
}
