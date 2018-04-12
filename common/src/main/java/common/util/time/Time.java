package common.util.time;

/**
 * Encapsulates a time service which supplies various figures such as the current time as millis since epoch.
 */
public interface Time {

    /**
     * @return the current time since epoch expressed as millis
     */
    long nowMillis();

}
