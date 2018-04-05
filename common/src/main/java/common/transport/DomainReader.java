package common.transport;

public interface DomainReader {

    BufferHandler handler();

    RawReader reader();

}
