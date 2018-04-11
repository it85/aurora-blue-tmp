package common.transport;

public interface RawReader {

    int DEFAULT_MESSAGE_SIZE = 512;

    void poll(BufferHandler handler);

}
