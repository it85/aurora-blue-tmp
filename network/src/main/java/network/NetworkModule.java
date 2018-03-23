package network;

import com.google.inject.AbstractModule;

public final class NetworkModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Socket.class).to(JettySocket.class);
    }
}