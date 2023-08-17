package me.saniukvyacheslav.definition.pattern;

/**
 * Observable interface marks class, that it's observable by single on more {@link Observer} observers.
 * Use {@link ObservableEvent} instances to notify observers about class events.
 */
public interface Observable {

    /**
     * Subscribe specified observer for observe for class events.
     * @param anObserver - observer.
     * @param anEvents - observable events.
     */
    void subscribe(Observer anObserver, ObservableEvent... anEvents);

    /**
     * Unsubscribe specified observer for observe for class events.
     * @param anObserver - observer.
     */
    void unsubscribe(Observer anObserver);

    /**
     * Notify all subscribed observers about any event.
     * @param anEvent - event.
     * @param anArguments - event arguments.
     */
    void notify(ObservableEvent anEvent, Object... anArguments);

    /**
     * Notify single observer about any event.
     * @param anObserver - observer.
     * @param anEvent - event
     * @param anArguments - event arguments.
     */
    void notify(Observer anObserver, ObservableEvent anEvent, Object... anArguments);
}
