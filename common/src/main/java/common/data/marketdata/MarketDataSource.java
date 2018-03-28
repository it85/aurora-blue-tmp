package common.data.marketdata;

import common.network.WssEndpoint;

public interface MarketDataSource<T> extends WssEndpoint {

    T convert(String quote);

}
