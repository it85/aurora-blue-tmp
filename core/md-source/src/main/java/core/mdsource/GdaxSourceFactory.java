package core.mdsource;

import common.data.marketdata.MarketDataSource;

final class GdaxSourceFactory implements MarketDataSourceFactory {

    @Override
    public MarketDataSource create(MarketDataSource.Type type) {
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
