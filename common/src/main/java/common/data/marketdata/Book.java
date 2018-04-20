package common.data.marketdata;

import common.data.type.Serializable;

/**
 * Encapsulates a full book for a venue, meaning both sides
 */
public interface Book extends Serializable {

    HalfBook bids();

    HalfBook asks();

    void clear();

}
