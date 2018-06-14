package common.data.marketdata;

import com.carrotsearch.hppc.LongLongMap;
import com.carrotsearch.hppc.LongLongScatterMap;

public class BasicHalfBook implements HalfBook {

    private final LongLongMap quotes;

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
        quotes = new LongLongScatterMap();
    }

    @Override
    public void update(double price, double size) {
        quotes.put(0, 0);
    }

    @Override
    public double size(double price) {
        return quotes.get(0);
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
    public LongLongMap rawValues() {
        return quotes;
    }
}
