package common.data.marketdata;

import common.data.messaging.Serializable;
import common.network.WssEndpoint;

public interface MarketDataSource<T extends Serializable> extends WssEndpoint {

    T convert(String quote);

}
