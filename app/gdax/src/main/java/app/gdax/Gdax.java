package app.gdax;

import com.google.gson.Gson;
import com.google.inject.Inject;
import common.data.marketdata.Instrument;
import common.data.marketdata.L3Quote;
import common.data.marketdata.MarketDataSource;
import network.WssEndpoint;

public class Gdax implements MarketDataSource, WssEndpoint {

    private static final String ENDPOINT = "wss://ws-feed.gdax.com";

    private final Instrument instrument;
    private final Gson gson;    // TODO: Get rid and use low-latency custom String parser

    @Inject
    Gdax() {
        this.instrument = new Instrument().symbol("BTC-USD");
        this.gson = new Gson();
    }

    @Override
    public L3Quote convert(String quote) {
        return gson.fromJson(quote, L3Quote.class);
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
}