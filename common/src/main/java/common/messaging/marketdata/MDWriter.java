package common.messaging.marketdata;

import common.data.marketdata.Book;
import common.data.marketdata.L3Quote;

/**
 * A generic interface for writing various types of market data to some data source
 */
public interface MDWriter {

    void l3Quote(L3Quote quote);

    void book(Book book);

}
