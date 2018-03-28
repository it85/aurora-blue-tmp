package chronicle;

import net.openhft.chronicle.queue.ChronicleQueueBuilder;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptTailer;

public class ChronicleFactory {

    private ChronicleFactory() {
        throw new UnsupportedOperationException();
    }

    public static ExcerptAppender acquireAppender(String path) {
        return ChronicleQueueBuilder.single(path).build().acquireAppender();
    }

    public static ExcerptTailer createTailer(String path) {
        return ChronicleQueueBuilder.single(path).build().createTailer();
    }
}
