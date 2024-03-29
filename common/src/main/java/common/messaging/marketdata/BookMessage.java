package common.messaging.marketdata;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import common.data.marketdata.BasicHalfBook;
import common.data.marketdata.Book;
import common.data.marketdata.HalfBook;
import common.data.type.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import transport.message.Book.BookProto;
import transport.message.Book.BookProto.Builder;

import java.nio.ByteBuffer;

public class BookMessage implements Book, Serializable<Book> {

    public static final short ID = 2;

    private static final Logger LOG = LogManager.getLogger(L3QuoteMessage.class);
    private static final int SIZE = 512;

    private final Builder builder = BookProto.newBuilder();
    private final Parser<BookProto> parser = builder.build().getParserForType();
    private final ByteBuffer buffer = ByteBuffer.allocate(SIZE);

    private HalfBook bids = new BasicHalfBook(false);
    private HalfBook asks = new BasicHalfBook(false);

    @Override
    public void reset() {

    }

    @Override
    public ByteBuffer asByteBuffer() {
        builder.clear();
        buffer.clear();

        buffer.put(builder.setId(ID).putAllBids(null)
                .build()
                .toByteArray());

        buffer.flip();
        return buffer;
    }

    @Override
    public Book from(ByteBuffer buffer) {
        try {
            init(parser.parseFrom(buffer.array(), 0, 19));  // TODO: need to encode message length, otherwise parse will fail
            return this;
        } catch (InvalidProtocolBufferException e) {
            LOG.error("Failed to parse {} with exception {}", L3QuoteMessage.class, e);
        }
        return null;
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

    private void init(BookProto proto) {
//        this.bids = proto.getBidsMap();
    }
}
