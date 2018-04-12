package common.util.time;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class TimeModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Time.class).to(SystemTime.class).in(Singleton.class);
    }
}
