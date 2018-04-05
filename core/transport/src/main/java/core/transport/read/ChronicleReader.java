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

    @Inject
    ChronicleReader(@Assisted String path) {
        tailer = ChronicleFactory.createTailer(path);
    }

    @Override
    public void poll(BufferHandler handler) {
        Bytes b = Bytes.wrapForWrite(ByteBuffer.allocate(512));
        if (tailer.readBytes(b)) {
            handler.handle(b.toTemporaryDirectByteBuffer());
        }
    }
}
