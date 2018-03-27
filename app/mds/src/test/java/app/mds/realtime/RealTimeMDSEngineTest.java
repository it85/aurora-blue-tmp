package app.mds.realtime;

import app.mds.MDSModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import common.data.marketdata.Book;
import common.data.marketdata.L3Quote;
import org.junit.Before;
import org.junit.Test;

public class RealTimeMDSEngineTest {

    private RealTimeMDSEngine<L3Quote> level3;
    private RealTimeMDSEngine<Book> level2;

    @Before
    public void setup() {
        Injector injector = Guice.createInjector(new MDSModule());
        level2 = injector.getInstance(Key.get(new TypeLiteral<RealTimeMDSEngine<Book>>(){}));
        level3 = injector.getInstance(Key.get(new TypeLiteral<RealTimeMDSEngine<L3Quote>>(){}));
    }

    @Test
    public void canConnectToLevel3() throws InterruptedException {
        level3.run();
        Thread.sleep(1000000);
    }

    @Test
    public void canConnectToLevel2() throws InterruptedException {
        level2.run();
        Thread.sleep(1000000);
    }
}
