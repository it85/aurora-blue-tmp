package common.network;

/**
 * Encapsulates connection details to a particular endpoint. Every WssEndpoint supplies it's connection URI as well as
 * the required initial handshake message. This handshake message is used to initiate a data streaming session.
 *
 * !! This class requires the client to bind to a concrete implementation. The Network module is NOT responsible for
 * providing a concrete.
 */
public interface WssEndpoint {

    String wss();

    String handshake();

}
