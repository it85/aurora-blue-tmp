package core.collection;

import com.google.inject.Inject;
import common.collection.SharedQueueBuffer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

final class ConcurrentQueue<T> implements SharedQueueBuffer<T> {

    private final Queue<T> q;

    @Inject
    ConcurrentQueue() {
        q = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean add(T t) {
        return q.add(t);
    }

    @Override
    public boolean offer(T t) {
        return q.offer(t);
    }

    @Override
    public T remove() {
        return q.remove();
    }

    @Override
    public T poll() {
        return q.poll();
    }

    @Override
    public T element() {
        return q.element();
    }

    @Override
    public T peek() {
        return q.peek();
    }
}
