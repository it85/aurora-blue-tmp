package common.messaging;

import common.data.marketdata.L3Type;
import common.messaging.marketdata.L3QuoteMessage;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This class validates that every message schema works as intended. Specifically, we're looking to assert that we can
 * successfully read and write given the internalized message schema.
 */
public class MessageSchemaTest {

    @Test
    public void l3QuoteMessageReadWriteTest() {
        L3Type type = L3Type.DONE;
        double price = 100.01;
        double size = 2.34562;

        L3QuoteMessage q = new L3QuoteMessage().type(type).price(price).size(size);
        ByteBuffer b = q.asByteBuffer();

        q.reset();

        assertNull(q.type());
        assertEquals(0, q.size(), 0);
        assertEquals(0, q.price(), 0);

        q.from(b);

        assertEquals(L3QuoteMessage.ID, b.getShort(0));
        assertEquals(type, q.type());
        assertEquals(price, q.price(), 0);
        assertEquals(size, q.size(), 0);
    }
}
