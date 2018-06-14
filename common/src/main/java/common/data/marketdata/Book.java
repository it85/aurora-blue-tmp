package common.data.marketdata;

/**
 * Encapsulates a full book for a venue, meaning both sides
 */
public interface Book {

    HalfBook bids();

    HalfBook asks();

    void clear();

}
