package me.saniukvyacheslav.util;

import lombok.experimental.UtilityClass;

/**
 * Different mathematics utilities.
 */
@UtilityClass
public final class MathUtils {

    /**
     * Generate random int from range.
     * @param aMin - minimum value.
     * @param aMax - maximum value.
     * @return - random int.
     */
    public static int generateRandomIntFromRange(int aMin, int aMax) {
        // Check arguments:
        if (aMax < aMin) throw new IllegalArgumentException("Maximum value [aMax] must be greater or equals minimum value [aMin].");

        // Generate random value from range:
        return aMin + ((int)(Math.random() * ((aMax - aMin) + 1)));
    }

}
