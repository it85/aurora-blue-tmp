package common.transport;

import java.nio.ByteBuffer;

public interface BufferHandler {

    void handle(ByteBuffer buffer);

}
