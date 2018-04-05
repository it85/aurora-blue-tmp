package common.network;

import common.messaging.MessageHandler;

/**
 * A web socket used to open a TCP connection with an endpoint.
 */
public interface Socket {

    void registerHandler(MessageHandler handler);

    /**
     * Open a connection to the specified endpoint.
     *
     * @throws IllegalStateException re-open an existing connection
     * @throws IllegalStateException open without first registering a handler
     */
    void open(String endpoint) throws IllegalStateException;

    /**
     * Close this socket
     */
    void close();

    /**
     * Send a message to the socket endpoint
     */
    void send(String message);

}
