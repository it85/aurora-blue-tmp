package common.data.marketdata;

public enum L3Type {

    // TODO: below are GDAX-specific types. Some might not be as generic as desired and should be normalized to something more general
    RECEIVED((short) 1),
    OPEN((short) 2),
    DONE((short) 3),
    MATCH((short) 4),
    CHANGE((short) 5),
    ACTIVATE((short) 6),
    UNKNOWN((short) 0);

    private final short code;

    L3Type(short code) {
        this.code = code;
    }

    public static L3Type from(String type) {
        type = type.toUpperCase();
        switch (type) {
            case "RECEIVED":
                return RECEIVED;
            case "OPEN":
                return OPEN;
            case "DONE":
                return DONE;
            case "MATCH":
                return MATCH;
            case "CHANGE":
                return CHANGE;
            case "ACTIVATE":
                return ACTIVATE;
            default:
                return UNKNOWN;
        }
    }

    public static L3Type from(short id) {
        switch (id) {
            case 1:
                return RECEIVED;
            case 2:
                return OPEN;
            case 3:
                return DONE;
            case 4:
                return MATCH;
            case 5:
                return CHANGE;
            case 6:
                return ACTIVATE;
            default:
                return UNKNOWN;
        }
    }

    public short code() {
        return code;
    }

}
