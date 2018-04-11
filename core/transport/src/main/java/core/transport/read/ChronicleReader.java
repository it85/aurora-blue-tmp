package core.transport.read;

import chronicle.ChronicleFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import common.transport.BufferHandler;
import common.transport.RawReader;
import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.queue.ExcerptTailer;

import java.nio.ByteBuffer;

final class ChronicleReader implements RawReader {

    private final ExcerptTailer tailer;
    private final ByteBuffer buffer;
    private final Bytes bytes;

    @Inject
    ChronicleReader(@Assisted String path) {
        tailer = ChronicleFactory.createTailer(path);   // TODO: add option for reading from end of chronicle rather than start
        buffer = ByteBuffer.allocate(DEFAULT_MESSAGE_SIZE);
        bytes = Bytes.wrapForWrite(buffer);
    }

    @Override
    public void poll(BufferHandler handler) {
        if (tailer.readBytes(bytes)) {
            handler.handle(buffer);
        }
    }
}
