package common.transport;

/**
 * There is no explicit implementation of this class -- guice handles this for us through an implicit binding (see
 * Assisted injection and how this interface is bound in the transport modules.
 */
public interface SerialReaderFactory {

    /**
     * A factory method which will return a new RawReader instance. This reader instance will reader messages from the
     * path specified.
     */
    RawReader create(String path);

}
