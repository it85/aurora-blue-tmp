package core.mdsource;

import com.google.gson.Gson;
import com.google.inject.Inject;
import common.data.marketdata.Instrument;
import common.data.marketdata.L3Type;
import common.data.marketdata.MarketDataSource;
import common.data.type.Serializable;
import common.messaging.marketdata.L3QuoteMessage;

final class L3Gdax implements MarketDataSource<Serializable> {

    private static final String ENDPOINT = "wss://ws-feed.gdax.com";

    private final Instrument instrument;
    private final Gson gson;    // TODO: Get rid and use low-latency custom String parser

    @Inject
    L3Gdax() {
        this.instrument = new Instrument().symbol("BTC-USD");   // TODO: Inject a symbol?
        this.gson = new Gson();
    }

    @Override
    public Serializable translate(String quote) {
        GdaxL3 l3 = gson.fromJson(quote, GdaxL3.class);
        return new L3QuoteMessage().type(L3Type.from(l3.type)).price(l3.price).size(l3.size);
    }

    @Override
    public String wss() {
        return ENDPOINT;
    }

    @Override
    public String handshake() {
        // TODO: use a more robust way to return the handshake
        return "{\"type\":\"subscribe\",\"product_ids\":[\"" + instrument.symbol() + "\"]}";
    }

    /**
     * A temporary class to assist in JSON de-serialization
     */
    private class GdaxL3 {
        private String type;
        private double price;
        private double size;
    }
}
