package me.saniukvyacheslav.definition;

/**
 * Closeable interface indicate that implemented class must close something.
 */
public interface Closeable {

    /**
     * Close something.
     * @throws Exception - If error occurs when close something.
     */
    void close() throws Exception;
}
