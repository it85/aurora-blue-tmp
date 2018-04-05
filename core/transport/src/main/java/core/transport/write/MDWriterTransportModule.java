package core.transport.write;

import com.google.inject.Singleton;
import common.messaging.MDWriter;

public class MDWriterTransportModule extends WriterTransportModule {

    @Override
    protected void configure() {
        super.configure();
        bind(MDWriter.class).to(ChronicleMDWriter.class).in(Singleton.class);
    }
}
