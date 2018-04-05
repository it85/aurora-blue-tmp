package common.data.marketdata;

import common.data.type.Reusable;
import common.data.type.Serializable;

// TODO: Refine this class to include exactly the fields we want to use
public final class L3Quote implements Serializable, Reusable {

    private String type;
    private double size;
    private double price;

    public String type() {
        return type;
    }

    public L3Quote type(String type) {
        this.type = type;
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

    @Override
    public short id() {
        return 0;
    }
}
