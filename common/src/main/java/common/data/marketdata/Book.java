package common.data.marketdata;

/**
 * Encapsulates the book for a single venue. Internally we represents quotes in a 2-D double array where the first
 * column is the price followed by the corresponding shares at that price level. Every row is a different price level.
 * Naturally the book is sorted depending on which side it's for. A book for bids will have the largest prices at the top
 * whereas a book for asks, i.e. offers, will have the smallest prices at the top.
 *
 * This abstraction supports real-time updates to the book, i.e. prices and quotes can be updated, inserted, and deleted.
 *
 * Example
 *
 * BID:
 * [100.01] [50]
 * [100.00] [100]
 * [99.99]  [150]
 *
 * ASK:
 * [100.02] [500]
 * [100.03] [140]
 * [100.04] [200]
 */
public class Book {

    private String product_id;
    private String type;
    private double[][] bids;
    private double[][] asks;

}
