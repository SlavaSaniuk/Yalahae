package me.saniukvyacheslav.util.array;

import me.saniukvyacheslav.util.string.YalahaeStrings;

/**
 * This utilities class defines helpful static methods for working with arrays.
 */
public final class ArrayUtils {

    /**
     * Private default constructor.
     */
    private ArrayUtils() {
        throw new UnsupportedOperationException(
                YalahaeStrings.utilitiesClassMessage(ArrayUtils.class));
    }

    /**
     * Check for specified element is last in specified array.
     * @param anArray - array.
     * @param anElement - element.
     * @return - true, if specified element last in specified array.
     */
    public static boolean isLastElement(Object[] anArray, Object anElement) {
        // Gel last element from collection:
        int lastElemPos = anArray.length-1;
        // Check:
        Object lastElem = anArray[lastElemPos];
        return lastElem == anElement;
    }


}
