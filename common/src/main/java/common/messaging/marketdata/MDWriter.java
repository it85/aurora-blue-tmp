package common.messaging.marketdata;

import common.data.marketdata.Book;
import common.data.marketdata.L3Quote;
import common.data.type.Serializable;

/**
 * A generic interface for writing various types of market data to some data source
 */
public interface MDWriter {
    // TODO: is this class even necessary for MDWriter anymore?

    void l3Quote(Serializable<L3Quote> quote);

    void book(Book book);

    void write(Serializable data);

}
