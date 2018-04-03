package core.collection;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import common.collection.SharedQueueBuffer;
import common.data.type.Serializable;

public class SingletonCollection<T> extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<SharedQueueBuffer<Serializable>>() {
        }).to(new TypeLiteral<ConcurrentQueue<Serializable>>() {
        }).in(Singleton.class);
    }
}
