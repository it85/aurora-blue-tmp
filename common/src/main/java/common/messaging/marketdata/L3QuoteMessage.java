package common.messaging.marketdata;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import common.data.marketdata.L3Quote;
import common.data.marketdata.L3Type;
import common.data.type.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import transport.message.L3Quote.L3QuoteProto;
import transport.message.L3Quote.L3QuoteProto.Builder;

import java.nio.ByteBuffer;

public final class L3QuoteMessage implements L3Quote, Serializable<L3Quote> {

    public static final short ID = 3;

    private static final Logger LOG = LogManager.getLogger(L3QuoteMessage.class);
    private static final int SIZE = 512;

    private final Builder builder = L3QuoteProto.newBuilder();
    private final Parser<L3QuoteProto> parser = builder.build().getParserForType();
    private final byte[] rawBuffer = new byte[SIZE];
    private final ByteBuffer buffer = ByteBuffer.allocate(SIZE);

    private L3Type type;
    private double size;
    private double price;

    @Override
    public ByteBuffer asByteBuffer() {
        builder.clear();
        buffer.clear();

//        buffer.put(builder.setId(ID).setType(type.code()).setPrice(price).setSize(size).build().toByteArray());
        int a = 3;
        int b = 3;
        double c = 3.0;
        double d = 3.0;
        final byte[] arr = builder.setId(a)
//                .setType(b)
//                .setSize(c)
//                .setPrice(d)
                .build().toByteArray();
        buffer.put(arr);
        buffer.flip();
//        buffer.rewind();
        buffer.compact();
        return buffer;
    }

    @Override
    public L3Quote from(ByteBuffer buffer) {
        try {
            init(parser.parseFrom(buffer.array()));
            return this;
        } catch (InvalidProtocolBufferException e) {
            LOG.error("Failed to parse {} with exception {}", L3QuoteMessage.class, e);
        }
        return null;
    }

    @Override
    public L3Type type() {
        return type;
    }

    public L3QuoteMessage type(L3Type type) {
        this.type = type;
        return this;
    }

    @Override
    public double size() {
        return size;
    }

    public L3QuoteMessage size(double size) {
        this.size = size;
        return this;
    }

    @Override
    public double price() {
        return price;
    }

    public L3QuoteMessage price(double price) {
        this.price = price;
        return this;
    }

    private void init(L3QuoteProto proto) {
        this.type = L3Type.from((short) proto.getType());
        this.size = proto.getSize();
        this.price = proto.getPrice();

    }

    @Override
    public void reset() {
        type = null;
        size = 0;
        price = 0;
    }
}
