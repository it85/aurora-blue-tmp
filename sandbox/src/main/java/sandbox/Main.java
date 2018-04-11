package sandbox;

import app.analytics.AnalyticsModule;
import app.mds.MDS;
import app.mds.MDSModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import core.transport.read.GenericReader;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MDSModule());
        Injector injector2 = Guice.createInjector(new AnalyticsModule());

        MDS mds = injector.getInstance(MDS.class);
        GenericReader reader = injector2.getInstance(GenericReader.class);

        new Thread(mds).start();
        new Thread(reader).start();
    }
}
