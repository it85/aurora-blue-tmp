package common.data.marketdata;

public class Instrument {

    private CharSequence symbol;

    public CharSequence symbol() {
        return symbol;
    }

    public Instrument symbol(CharSequence symbol) {
        this.symbol = symbol;
        return this;
    }
}
