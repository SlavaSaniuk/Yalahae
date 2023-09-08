package me.saniukvyacheslav.util.string;

import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * Different methods for working with Java {@link String} strings.
 */
@UtilityClass
public final class StringUtils {

    /**
     * Check specified string for null value.
     * If string is null, throw new NullPointerException with message "String [aParamName] must be not null.".
     * @param aString - string for checking.
     * @param aParamName - parameter name for exception message.
     */
    public static void checkForNull(String aString, String aParamName) {
        Objects.requireNonNull(aString, String.format("String [%s] must be not null.", aParamName));
    }

    /**
     * Check specified string for empty value.
     * If string is empty, throw new IllegalArgumentException with message "String [aParamName] must be not empty.".
     * @param aString - string for checking.
     * @param aParamName - parameter name for exception message.
     */
    public static void checkIfEmpty(String aString, String aParamName) {
        if (aString.isEmpty()) throw new IllegalArgumentException(String.format("String [%s] must be not empty.", aParamName));
    }

    /**
     * Check specified string for null and empty value.
     * If string is null, throw new NullPointerException with message "String [aParamName] must be not null.".
     * If string is empty, throw new IllegalArgumentException with message "String [aParamName] must be not empty.".
     * @param aString - string for checking.
     * @param aParamName - parameter name for exception message.
     */
    public static void checkString(String aString, String aParamName) {
        StringUtils.checkForNull(aString, aParamName);
        StringUtils.checkIfEmpty(aString, aParamName);
    }

}
