package common.collection;

import java.util.Queue;

public interface BoundedQueue<T> extends Queue<T> {

    int capacity();

    boolean isFull();

    /**
     * Returns the element at the index. This is known to be an inefficient because queues do not have a concept of
     * retrieval other than pop. Use sparingly with foreknowledge of this inefficiency.
     */
    T get(int index);

}
