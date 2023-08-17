package me.saniukvyacheslav.definition;

/**
 * Initializable interface indicate that implemented class must be initialized.
 */
public interface Initializable {

    /**
     * Init instance.
     * @param arguments - initialization arguments.
     * @throws Exception - If initialization error occurs.
     */
    void init(Object... arguments) throws Exception;

}
