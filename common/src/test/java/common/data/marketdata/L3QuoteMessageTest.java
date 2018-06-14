package common.data.marketdata;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class L3QuoteMessageTest {

    /**
     * Confirm that all enum codes are unique and map correctly
     */
    @Test
    public void testTypeEnumCodeUniqueness() {
        Set<Short> codes = new HashSet<>();

        codes.add(L3Type.RECEIVED.code());
        codes.add(L3Type.OPEN.code());
        codes.add(L3Type.DONE.code());
        codes.add(L3Type.MATCH.code());
        codes.add(L3Type.CHANGE.code());
        codes.add(L3Type.ACTIVATE.code());
        codes.add(L3Type.UNKNOWN.code());

        assertEquals(7, codes.size());
    }

    /**
     * Verify the String mapping behavior
     */
    @Test
    public void testTypeEnumStringConversion() {
        assertEquals(L3Type.RECEIVED, L3Type.from("RECEIVED"));
        assertEquals(L3Type.OPEN, L3Type.from("OPEN"));
        assertEquals(L3Type.DONE, L3Type.from("DONE"));
        assertEquals(L3Type.MATCH, L3Type.from("MATCH"));
        assertEquals(L3Type.CHANGE, L3Type.from("CHANGE"));
        assertEquals(L3Type.ACTIVATE, L3Type.from("ACTIVATE"));
        assertEquals(L3Type.UNKNOWN, L3Type.from("ASDF"));
    }

    /**
     * Verify the code mapping behavior
     */
    @Test
    public void testTypeEnumCodeConversion() {
        assertEquals(L3Type.RECEIVED, L3Type.from((short) 1));
        assertEquals(L3Type.OPEN, L3Type.from((short) 2));
        assertEquals(L3Type.DONE, L3Type.from((short) 3));
        assertEquals(L3Type.MATCH, L3Type.from((short) 4));
        assertEquals(L3Type.CHANGE, L3Type.from((short) 5));
        assertEquals(L3Type.ACTIVATE, L3Type.from((short) 6));
        assertEquals(L3Type.UNKNOWN, L3Type.from((short) 7));
    }

}
