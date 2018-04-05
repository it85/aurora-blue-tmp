package common.transport;

import java.nio.ByteBuffer;

public interface SerialWriter {

    /**
     * Writes this byte buffer to some underlying data source
     */
    void write(ByteBuffer buffer);

}
