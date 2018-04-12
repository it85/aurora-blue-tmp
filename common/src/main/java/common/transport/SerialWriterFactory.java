package common.transport;

/**
 * There is no explicit implementation of this class -- guice handles this for us through an implicit binding (see
 * Assisted injection and how this interface is bound in the module).
 */
public interface SerialWriterFactory {

    /**
     * A factory method which will return a new SerialWriter instance. This writer instance will write messages to the
     * path specified.
     */
    SerialWriter create(String path);

}
