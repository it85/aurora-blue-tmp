package common.transport;

import common.data.marketdata.Book;

import java.nio.ByteBuffer;

public interface SerialWriter {

    /**
     * Writes this byte buffer to some underlying data source
     */
    void write(ByteBuffer buffer);

    /**
     * !!! This is a temporary API for until we can figure out how to best serialize and write a book object to chronicle
     */
    void write(Book book);

}
