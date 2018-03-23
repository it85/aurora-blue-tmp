package network;

import com.google.inject.Inject;
import common.messaging.MessageHandler;

/**
 * Manipulates socket connections and is responsible for bringing up/shutting down sessions
 */
public final class SocketManager {

    private final Socket socket;
    private final WssEndpoint endpoint;

    @Inject
    public SocketManager(Socket socket, WssEndpoint endpoint) {
        this.socket = socket;
        this.endpoint = endpoint;
    }

    public void begin(MessageHandler handler) {
        initialize(handler);
        begin();
    }

    public void shutdown() {
        socket.close();
    }

    private void initialize(MessageHandler handler) {
        socket.registerHandler(handler);
        socket.open(endpoint.wss());
    }

    private void begin() {
        socket.send(endpoint.handshake());
    }
}
