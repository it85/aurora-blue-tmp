package app.gdax;

import com.google.gson.Gson;
import com.google.inject.Inject;
import common.data.marketdata.Book;
import common.data.marketdata.Instrument;
import common.data.marketdata.MarketDataSource;
import common.network.WssEndpoint;

public class L2Gdax implements MarketDataSource<Book>, WssEndpoint {

    private static final String ENDPOINT = "wss://ws-feed.gdax.com";

    private final Instrument instrument;
    private final Gson gson;    // TODO: Get rid and use low-latency custom String parser

    @Inject
    L2Gdax() {
        this.instrument = new Instrument().symbol("BTC-USD");   // TODO: Inject a symbol?
        this.gson = new Gson();
    }

    @Override
    public Book convert(String quote) {
        return gson.fromJson(quote, Book.class);
    }

    @Override
    public String wss() {
        return ENDPOINT;
    }

    @Override
    public String handshake() {
        // TODO: use a more robust way to return the handshake
        return "{\"type\":\"subscribe\"," +
                "\"product_ids\":[\"" + instrument.symbol() + "\"]," +
                "\"channels\":[\"level2\"]" +
                "}";
    }
}
