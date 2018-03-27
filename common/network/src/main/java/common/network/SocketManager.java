package common.network;

import com.google.inject.Inject;
import common.data.messaging.MessageHandler;

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

    public void begin(MessageHandler handler, WssEndpoint endpoint) {
        this.endpoint = endpoint;
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
