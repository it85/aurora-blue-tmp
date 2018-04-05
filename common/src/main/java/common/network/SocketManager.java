package common.network;

import com.google.inject.Inject;
import common.messaging.MessageHandler;

/**
 * Manipulates socket connections and is responsible for bringing up/shutting down sessions
 */
public final class SocketManager {

    private final Socket socket;

    private WssEndpoint endpoint;

    @Inject
    public SocketManager(Socket socket) {
        this.socket = socket;
    }

    public void init(MessageHandler handler, WssEndpoint endpoint) {
        this.endpoint = endpoint;
        initialize(handler);
    }

    public void begin() {
        socket.send(endpoint.handshake());
    }

    public void shutdown() {
        socket.close();
    }

    private void initialize(MessageHandler handler) {
        socket.registerHandler(handler);
        socket.open(endpoint.wss());
    }
}
