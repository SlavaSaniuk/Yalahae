package me.saniukvyacheslav.definition.pattern;

/**
 * Observer interface indicate that implemented class observe one are more {@link Observable} observable objects
 * for {@link ObservableEvent} events/
 */
public interface Observer {

    /**
     * Do something on event.
     * @param anEvent - event.
     * @param anArguments - event arguments.
     */
    void onObservableEvent(ObservableEvent anEvent, Object... anArguments);

}
