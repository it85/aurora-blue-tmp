package common.analytics.turnover;

/**
 * Tracks a single turnover metric
 */
public interface Turnover {

    /**
     * Invoke this API to inform Turnover that a new event was seen
     */
    void newEvent();

    /**
     * @return the current turnover expressed as an average of all elapsed times seen thus far
     */
    double turnover();
}
