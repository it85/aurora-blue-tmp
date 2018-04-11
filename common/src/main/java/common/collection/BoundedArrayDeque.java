package common.collection;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * A linked queue queue that is bounded in maximum capacity, i.e. it will automatically discard the oldest element when
 * adding a new element when there is no longer any vacancy. BoundedArrayDeque guarantees that elements are only
 * potentially auto-discarded during insertion operations.
 * <p>
 * {@link BoundedArrayDeque} always inserts at index 0 which is internally referred to as the tail end of the queue.
 * The headIndex therefore represents the index of the front, or head, of the queue and is by definition the index holding
 * the oldest element in the structure.
 */
public final class BoundedArrayDeque<T> implements BoundedQueue<T> {

    private final Queue<T> queue;
    private final int capacity;

    public BoundedArrayDeque(int capacity) {
        this.capacity = capacity;
        queue = new ArrayDeque<>(capacity);
    }

    public int capacity() {
        return capacity;
    }

    public boolean isFull() {
        return capacity == queue.size();
    }

    @SuppressWarnings("unchecked")
    public T get(int i) {
        T[] arr = (T[]) queue.toArray();
        return arr[i];
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean contains(Object o) {
        return queue.contains(o);
    }

    public Iterator<T> iterator() {
        return queue.iterator();
    }

    public Object[] toArray() {
        return queue.toArray();
    }

    public <E> E[] toArray(E[] a) {
        return queue.toArray(a);
    }

    public boolean add(T t) {
        if (size() == capacity) {
            queue.remove();
            return queue.add(t);
        } else {
            return queue.add(t);
        }
    }

    public boolean remove(Object o) {
        return queue.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return queue.addAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    public void clear() {
        queue.clear();
    }

    public boolean offer(T t) {
        return queue.offer(t);
    }

    public T remove() {
        return queue.remove();
    }

    public T poll() {
        return queue.poll();
    }

    public T element() {
        return queue.element();
    }

    public T peek() {
        return queue.peek();
    }
}
