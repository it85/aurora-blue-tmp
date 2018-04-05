package common.messaging;

import common.data.marketdata.L3Quote;

import java.nio.ByteBuffer;

public class L3QuoteMessage {

    /**
     * The type this object is; used for message handling purposes
     */
    public static final short ID = 0;

    /**
     * Below is the message schema for a given L3Quote object. <field_name>I represents the index used to store this
     * field.
     */
    private static final int TYPE_I = 1;
    private static final int SIZE_I = 2;
    private static final int PRICE_I = 3;

    private static final ByteBuffer BUFFER = ByteBuffer.allocate(512);

    public static ByteBuffer pack(L3Quote quote) {
        BUFFER.clear();
        BUFFER.putShort(0, quote.id());
        BUFFER.putDouble(SIZE_I, quote.size());
        BUFFER.putDouble(PRICE_I, quote.price());
        return BUFFER;
    }

    public static void parse(L3Quote q, ByteBuffer b) {
        q.reset();
        q.size(b.getDouble(SIZE_I)).price(b.getDouble(PRICE_I));
    }
}
