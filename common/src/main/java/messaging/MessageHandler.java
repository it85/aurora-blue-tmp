package messaging;

/**
 * A generic interface handling String-based messages. Recommend only using for low-level communication between core
 * infrastructural components, e.g. network sockets
 */
public interface MessageHandler {

    void onMessage(String message);

}
