package common.data.marketdata;

import common.data.marketdata.L3Quote.Type;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class L3QuoteTest {

    /**
     * Confirm that all enum codes are unique and map correctly
     */
    @Test
    public void testTypeEnumCodeUniqueness() {
        Set<Short> codes = new HashSet<>();

        codes.add(Type.RECEIVED.code());
        codes.add(Type.OPEN.code());
        codes.add(Type.DONE.code());
        codes.add(Type.MATCH.code());
        codes.add(Type.CHANGE.code());
        codes.add(Type.ACTIVATE.code());
        codes.add(Type.UNKNOWN.code());

        assertEquals(7, codes.size());
    }

    /**
     * Verify the String mapping behavior
     */
    @Test
    public void testTypeEnumStringConversion() {
        assertEquals(Type.RECEIVED, Type.from("RECEIVED"));
        assertEquals(Type.OPEN, Type.from("OPEN"));
        assertEquals(Type.DONE, Type.from("DONE"));
        assertEquals(Type.MATCH, Type.from("MATCH"));
        assertEquals(Type.CHANGE, Type.from("CHANGE"));
        assertEquals(Type.ACTIVATE, Type.from("ACTIVATE"));
        assertEquals(Type.UNKNOWN, Type.from("ASDF"));
    }

    /**
     * Verify the code mapping behavior
     */
    @Test
    public void testTypeEnumCodeConversion() {
        assertEquals(Type.RECEIVED, Type.from((short) 1));
        assertEquals(Type.OPEN, Type.from((short) 2));
        assertEquals(Type.DONE, Type.from((short) 3));
        assertEquals(Type.MATCH, Type.from((short) 4));
        assertEquals(Type.CHANGE, Type.from((short) 5));
        assertEquals(Type.ACTIVATE, Type.from((short) 6));
        assertEquals(Type.UNKNOWN, Type.from((short) 7));
    }

}
