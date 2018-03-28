package core.transport;

import chronicle.ChronicleFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import common.data.messaging.Serializable;
import common.transport.SerialWriter;
import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.queue.ExcerptAppender;

public final class ChronicleWriter implements SerialWriter {

    private final ExcerptAppender appender;

    @Inject
    public ChronicleWriter(@Assisted String path) {
        appender = ChronicleFactory.acquireAppender(path);
    }

    @Override
    public boolean write(Serializable message) {
        Bytes b = Bytes.wrapForWrite(message.serialize());  // TODO: reduce garbage
        appender.writeBytes(b);
        return false;
    }
}
