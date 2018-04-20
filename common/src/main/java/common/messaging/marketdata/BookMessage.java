package common.messaging.marketdata;

import common.data.marketdata.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;

public class BookMessage {

    private static final Logger LOG = LogManager.getLogger(BookMessage.class);

    /**
     * The type this object is; used for message handling purposes
     */
    public static final short ID = 2;

    /**
     * Below is the message schema for a given Book object. <field_name>I represents the byte-offset used to store this
     * field.
     */
    private static final int TYPE_I = 2;
    private static final int SIZE_I = 4;
    private static final int PRICE_I = 12;
    private static final int LENGTH = 20;

    private static final ByteBuffer BUFFER = ByteBuffer.allocate(LENGTH);

    // TODO: abstract out the header writing portion and create a more robust message schema
    public static ByteBuffer pack(Book b) {
        if (!valid(b)) {
            LOG.error("Tried packing an invalid Book message: {}", b);
            System.exit(0); // TODO: need to come up with a graceful way handle and recover from a corrupt message
        }

        BUFFER.clear();
        BUFFER.putShort(0, ID); // TODO: this should be written in some kind of header which would include ID and timestamp








        BUFFER.rewind();    // TODO: is rewind necessary?
        return BUFFER;
    }

    public static void parse(Book book, ByteBuffer b) {
    }

    private static boolean valid(Book b) {
        return true;
    }

}
