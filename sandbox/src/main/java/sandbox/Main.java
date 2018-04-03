package sandbox;

import app.mds.MDS;
import app.mds.MDSModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MDSModule());
        MDS mds = injector.getInstance(MDS.class);

        new Thread(mds).start();
    }
}
