package common.transport.ipc;

import common.transport.SerialWriter;

public interface SerialWriterFactory {

    /**
     * A factory method which will return a new SerialWriter instance. This writer instance will write messages to the
     * path specified.
     */
    SerialWriter create(String path);

}
