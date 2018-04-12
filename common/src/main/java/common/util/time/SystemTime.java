package common.util.time;

final class SystemTime implements Time {

    @Override
    public long nowMillis() {
        return System.currentTimeMillis();
    }
}
