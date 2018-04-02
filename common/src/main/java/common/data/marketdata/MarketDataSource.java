package common.data.marketdata;

import common.data.type.Serializable;
import common.network.WssEndpoint;

public interface MarketDataSource<T extends Serializable> extends WssEndpoint {

    enum Type {
        L1,
        L2,
        L3
    }

    T convert(String quote);

}
