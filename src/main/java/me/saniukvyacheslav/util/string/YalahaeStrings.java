package me.saniukvyacheslav.util.string;

/**
 * This class defines methods to get common formatted string for using it in this library.
 */
public final class YalahaeStrings {

    /**
     * Private default constructor.
     */
    private YalahaeStrings() {
        throw new UnsupportedOperationException(
                YalahaeStrings.utilitiesClassMessage(YalahaeStrings.class));
    }

    /**
     * Get "[CLASS_NAME] is utilities class. Utilities class is not instantiable and inheritable." string.
     * @param anUtilitiesClass - class name.
     * @return - string.
     */
    public static String utilitiesClassMessage(Class<?> anUtilitiesClass) {
        return String.format("[%s] is utilities class. Utilities class is not instantiable and inheritable.",
                anUtilitiesClass.getCanonicalName());
    }
}
