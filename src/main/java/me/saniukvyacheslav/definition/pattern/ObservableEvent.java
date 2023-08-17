package me.saniukvyacheslav.definition.pattern;

/**
 * ObservableEvent represent simple event with code.
 * ObservableEvent interface define single method to get code of any event.
 */
public interface ObservableEvent {

    /**
     * Get event code.
     * @return - event code.
     */
    int getEventCode();
}
