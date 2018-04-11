package common.messaging;

import common.data.marketdata.L3Quote;
import common.data.marketdata.L3Quote.Type;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;

/**
 * This class validates that every message schema works as intended. Specifically, we're looking to assert that we can
 * successfully read and write given the internalized message schema.
 */
public class MessageSchemaTest {

    @Test
    public void l3QuoteMessageReadWriteTest() {
        Type type = Type.DONE;
        double price = 100.01;
        double size = 2.34562;

        L3Quote q = new L3Quote().type(type).price(price).size(size);
        ByteBuffer b = L3QuoteMessage.pack(q);

        L3Quote qActual = new L3Quote();
        L3QuoteMessage.parse(qActual, b);

        assertEquals(L3QuoteMessage.ID, b.getShort(0));
        assertEquals(type, qActual.type());
        assertEquals(price, qActual.price(), 0);
        assertEquals(size, qActual.size(), 0);
    }
}
