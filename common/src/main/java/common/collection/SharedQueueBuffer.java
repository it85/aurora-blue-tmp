package common.collection;

/**
 * A simple queue which offers concurrent READ and WRITE access.
 *
 * @param <T> the type to be stored
 */
// TODO: let a high-performing library implement this interface
public interface SharedQueueBuffer<T> {

    boolean add(T t);

    boolean offer(T t);

    T remove();

    T poll();

    T element();

    T peek();
}
