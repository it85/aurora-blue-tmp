package sandbox;

import app.mds.MDS;
import app.mds.MDSModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import core.transport.read.GenericReader;
import core.transport.read.MDReaderTransportModule;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MDSModule());
        Injector injector2 = Guice.createInjector(new MDReaderTransportModule());
        MDS mds = injector.getInstance(MDS.class);
        GenericReader reader = injector2.getInstance(GenericReader.class);

        new Thread(mds).start();

        new Thread(reader).start();
    }
}
