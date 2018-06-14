package common.data.marketdata;

import java.nio.ByteBuffer;

public class BasicBook implements Book {

    private final HalfBook bids;
    private final HalfBook asks;

    public BasicBook() {
        bids = new BasicHalfBook(true);
        asks = new BasicHalfBook(false);
    }

    @Override
    public HalfBook bids() {
        return bids;
    }

    @Override
    public HalfBook asks() {
        return asks;
    }

    @Override
    public void clear() {
        bids.clear();
        asks.clear();
    }

    @Override
    public ByteBuffer asByteBuffer() {
        return null;
    }

    @Override
    public Object from(ByteBuffer buffer) {
        return null;
    }

    @Override
    public void reset() {

    }
}
