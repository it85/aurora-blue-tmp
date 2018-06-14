package common.data.type;

import java.nio.ByteBuffer;

public interface Serializable<T> extends Reusable {

    ByteBuffer asByteBuffer();

    T from(ByteBuffer buffer);

}
