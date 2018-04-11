package common.data.marketdata;

import common.data.type.Reusable;
import common.data.type.Serializable;

// TODO: Refine this class to include exactly the fields we want to use
public final class L3Quote implements Serializable, Reusable {

    private Type type;
    private double size;
    private double price;

    public Type type() {
        return type;
    }

    public L3Quote type(Type type) {
        this.type = type;
        return this;
    }

    public L3Quote type(String type) {
        this.type = Type.from(type);
        return this;
    }

    public L3Quote type(short type) {
        this.type = Type.from(type);
        return this;
    }

    public double size() {
        return size;
    }

    public L3Quote size(double size) {
        this.size = size;
        return this;
    }

    public double price() {
        return price;
    }

    public L3Quote price(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "L3Quote{" +
                "type='" + type + '\'' +
                ", size=" + size +
                ", price=" + price +
                '}';
    }

    @Override
    public void reset() {
        type = null;
        size = 0;
        price = 0;
    }

    public enum Type {
        // TODO: below are GDAX-specific types. Some might not be as generic as desired and should be normalized to something more general
        RECEIVED((short) 1),
        OPEN((short) 2),
        DONE((short) 3),
        MATCH((short) 4),
        CHANGE((short) 5),
        ACTIVATE((short) 6),
        UNKNOWN((short) 0);

        private final short code;

        Type(short code) {
            this.code = code;
        }

        public static Type from(String type) {
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

        public static Type from(short id) {
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
}
