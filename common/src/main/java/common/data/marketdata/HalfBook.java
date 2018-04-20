package common.data.marketdata;

import java.util.Map;

/**
 * Encapsulates a set of quotes, and various possible operations, for a particular side, either bid or ask.
 */
public interface HalfBook {

    void update(double price, double size);

    double size(double price);

    int size();

    boolean empty();

    double insidePrice();

    double insideSize();

    void clear();

    Map<Double, Double> rawValues();

}
