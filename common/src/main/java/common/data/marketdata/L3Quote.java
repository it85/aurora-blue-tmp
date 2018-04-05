package common.data.marketdata;

import common.data.type.Serializable;

// TODO: Refine this class to include exactly the fields we want to use
public final class L3Quote implements Serializable {

    /**
     * The type this object is; used for message handling purposes
     */
    public static final short ID = 0;

    /**
     * Below is the message schema for a given L3Quote object. <field_name>I represents the index used to store this
     * field.
     */
    private static final int TYPE_I = 1;
    private static final int SIZE_I = 2;
    private static final int PRICE_I = 3;

    private String type;
    private double size;
    private double price;

    @Override
    public short id() {
        return ID;
    }

    public static int typeI() {
        return TYPE_I;
    }

    public static int sizeI() {
        return SIZE_I;
    }

    public static int priceI() {
        return PRICE_I;
    }

    public String type() {
        return type;
    }

    L3Quote type(String type) {
        this.type = type;
        return this;
    }

    public double size() {
        return size;
    }

    L3Quote size(double size) {
        this.size = size;
        return this;
    }

    public double price() {
        return price;
    }

    L3Quote price(double price) {
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
}
