package core.transport.write;

import chronicle.ChronicleFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import common.transport.SerialWriter;
import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.queue.ExcerptAppender;

import java.nio.ByteBuffer;

final class ChronicleWriter implements SerialWriter {

    private final ExcerptAppender appender;

    @Inject
    ChronicleWriter(@Assisted String path) {
        appender = ChronicleFactory.acquireAppender(path);
    }

    @Override
    public void write(ByteBuffer buffer) {
        // TODO: improve this so that we don't create garbage when wrapping for reading -- should just iterate over the buffer raw
        Bytes bytes = Bytes.wrapForRead(buffer);
        appender.writeBytes(bytes);
    }
}
