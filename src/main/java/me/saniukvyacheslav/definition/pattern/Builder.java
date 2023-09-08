package me.saniukvyacheslav.definition.pattern;

/**
 * "Builder" programming pattern.
 * @param <T> - under construction class.
 */
public interface Builder<T> {

    /**
     * Build instance of under construction class.
     * @return - instance.
     */
    T build();
}
