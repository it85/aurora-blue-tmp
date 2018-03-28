package core.network;

import com.google.inject.AbstractModule;
import common.network.Socket;
import common.network.SocketManager;

public final class NetworkModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SocketManager.class);
        bind(Socket.class).to(JettySocket.class);
    }
}
