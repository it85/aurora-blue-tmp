package common.messaging;

import common.data.marketdata.L3Quote;

import java.nio.ByteBuffer;

/**
 * A generic interface for reading market data raw data.
 *
 * Every API is designed to take in the target object and the raw data encapsulated in a buffer. The function is then
 * responsible for populating the target with the data found on the buffer
 */
public interface MDDispatcher {

    L3Quote l3Quote(ByteBuffer buffer);

}
