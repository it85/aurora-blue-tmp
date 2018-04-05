package common.messaging;

import common.data.marketdata.L3Quote;

/**
 * A generic interface for handling different types of market data
 */
public interface MDHandler {

    void handle(L3Quote quote);

}
