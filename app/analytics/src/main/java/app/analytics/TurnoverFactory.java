package app.analytics;

/**
 * There is no explicit implementation of this class -- guice handles this for us through an implicit binding (see
 * Assisted injection and how this interface is bound in the module).
 */
interface TurnoverFactory {

    Turnover create(int period);

}
