package core.mdsource;

import com.google.gson.Gson;
import com.google.inject.Inject;
import common.data.marketdata.BasicBook;
import common.data.marketdata.Book;
import common.data.marketdata.Instrument;
import common.data.marketdata.MarketDataSource;
import common.data.type.Serializable;
import common.messaging.marketdata.BookMessage;

import java.util.HashMap;
import java.util.Map;

final class L2Gdax implements MarketDataSource<Serializable> {

    private static final String ENDPOINT = "wss://ws-feed.gdax.com";    // TODO: Refactor into a common class

    private final Instrument instrument;
    private final Gson gson;    // TODO: Get rid and use low-latency custom String parser
    private final Book book;

    @Inject
    L2Gdax() {
        this.instrument = new Instrument().symbol("BTC-USD");   // TODO: Inject a symbol?
        this.gson = new Gson();

        book = new BasicBook();
    }

    @Override
    public Serializable translate(String quote) {
//        GdaxL2Snapshot snapshot = gson.fromJson(quote, GdaxL2Snapshot.class);
//
//        switch(snapshot.type) {
//            case GdaxL2Snapshot.CHANGE:
//                update(snapshot);
//                return book;
//            case GdaxL2Snapshot.SNAPSHOT:
//                snapshot(snapshot);
//                return book;
//            default:
//                return book;
//        }
        Map<String, String> map = new HashMap<>();
        map.put("testKey", "elmo");
        return new BookMessage().storeBids(map);
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

    // TODO: is it possible a snapshot could have both sell/buy changes?
    private void update(GdaxL2Snapshot snapshot) {
        if (snapshot.changes[0][0].equals("sell")) {
            book.asks().update(Double.parseDouble(snapshot.changes[0][1]), Double.parseDouble(snapshot.changes[0][2]));
        } else {
            book.bids().update(Double.parseDouble(snapshot.changes[0][1]), Double.parseDouble(snapshot.changes[0][2]));
        }
    }

    private void snapshot(GdaxL2Snapshot snapshot) {
        book.clear();

        for (int i = 0; i < snapshot.bids.length; i++) {
            book.bids().update(Double.parseDouble(snapshot.bids[i][0]), Double.parseDouble(snapshot.bids[i][1]));
        }

        for (int i = 0; i < snapshot.asks.length; i++) {
            book.asks().update(Double.parseDouble(snapshot.asks[i][0]), Double.parseDouble(snapshot.asks[i][1]));
        }
    }

    private final class GdaxL2Snapshot {

        private static final String CHANGE = "l2update";
        private static final String SNAPSHOT = "snapshot";

        private String type;
        private String[][] bids;
        private String[][] asks;
        private String[][] changes;
    }
}
