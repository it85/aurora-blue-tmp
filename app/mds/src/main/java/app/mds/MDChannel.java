package app.mds;

import common.messaging.MessageHandler;

/**
 * A single market data channel which supports a specific form of market data, e.g. L1, L2, or L3. Each channel spawns its
 * own thread for performing its work.s
 */
public interface MDChannel extends Runnable, MessageHandler {
}
