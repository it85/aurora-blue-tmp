package common.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Verify that the Socket is able to establish a connection and successfully handle a message. We use a real GDAX socket
 * connection.
 *
 * !!If GDAX one day changes their socket protocol, this test will fail.
 */
public class SocketTest {

    private static final String ENDPOINT = "wss://ws-feed.gdax.com";
    private static final String SUBSCRIBE = "{\"type\":\"subscribe\",\"product_ids\":[\"BTC-USD\"]}";

    private Socket socket;

    @Before
    public void setup() {
        socket = new JettySocket();
        socket.registerHandler(message -> {
            message = message.toLowerCase();
            assertNotNull(message);
            assertFalse(message.contains("error"));
        });
    }

    @After
    public void cleanup() {
        socket.close();
    }

    @Test
    public void socketConnection() throws InterruptedException {
        socket.open(ENDPOINT);
        socket.send(SUBSCRIBE);
        Thread.sleep(1000);     // allow for sufficient time for the socket to respond
    }
}
