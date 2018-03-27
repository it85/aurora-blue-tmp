package common.data.marketdata;

public interface MarketDataSource<T> {

    T convert(String quote);

}
