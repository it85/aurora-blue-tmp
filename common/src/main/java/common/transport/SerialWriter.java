package common.transport;

import common.data.messaging.Serializable;

public interface SerialWriter {

    /**
     * Writes this serializable message to some underlying data storage.
     *
     * @return true if the message was successfully committed
     */
    boolean write(Serializable message);

}
