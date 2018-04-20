package common.data.marketdata;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicHalfBook implements HalfBook {

    private final Map<Double, Double> quotes;

    /**
     * Instantiate a new BasicHalfBook instance
     * @param reverse if we want to store quotes in reverse natural order. If true, we will store in order of largest to
     *                smallest whereas false means we will store from smallest to largest. Largest to smallest means the
     *                largest quote is at index 0.
     */
    public BasicHalfBook(boolean reverse) {
//        if (reverse) {
//            quotes = new TreeMap<>(Collections.reverseOrder());
//        } else {
//            quotes = new TreeMap<>();
//        }
        quotes = new ConcurrentHashMap<>();
    }

    @Override
    public void update(double price, double size) {
        quotes.put(price, size);
    }

    @Override
    public double size(double price) {
        return quotes.get(price);
    }

    @Override
    public int size() {
        return quotes.size();
    }

    @Override
    public boolean empty() {
        return quotes.isEmpty();
    }

    @Override
    public double insidePrice() {
        return 0;
    }

    @Override
    public double insideSize() {
        return 0;
    }

    @Override
    public void clear() {
        quotes.clear();
    }

    @Override
    public Map<Double, Double> rawValues() {
        return quotes;
    }
}
