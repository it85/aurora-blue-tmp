package common.util;

import common.collection.BoundedQueue;

public final class MathUtil {

    /**
     * Returns the average of all values in this queue
     * @param q the queue that we wish to compute an average for
     * @param <T> the data type stored in the queue. Must extend Number
     * @return the average of all values in the queue
     */
    public static <T extends Number> double average(BoundedQueue<T> q) {
        double runningTotal = 0;
        for (int i = 0; i < q.size(); i++) {
            runningTotal += q.get(i).doubleValue();
        }
        return runningTotal / q.size();
    }

}
