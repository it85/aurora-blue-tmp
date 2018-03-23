package app.mds.realtime;

import app.mds.MDSModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

public class RealTimeMDSEngineTest {

    private RealTimeMDSEngine realTimeMDS;

    @Before
    public void setup() {
        Injector injector = Guice.createInjector(new MDSModule());
        realTimeMDS = injector.getInstance(RealTimeMDSEngine.class);
    }

    @Test
    public void canConnectToEndpoint() throws InterruptedException {
        realTimeMDS.run();
        Thread.sleep(10000);
    }
}
