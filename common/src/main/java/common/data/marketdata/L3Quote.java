package common.data.marketdata;

public final class L3Quote {

    private String type;
    private double size;
    private double price;

    String type() {
        return type;
    }

    L3Quote type(String type) {
        this.type = type;
        return this;
    }

    double size() {
        return size;
    }

    L3Quote size(double size) {
        this.size = size;
        return this;
    }

    double price() {
        return price;
    }

    L3Quote price(double price) {
        this.price = price;
        return this;
    }
}
