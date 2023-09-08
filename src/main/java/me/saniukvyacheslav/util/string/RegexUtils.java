package me.saniukvyacheslav.util.string;

import lombok.experimental.UtilityClass;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helpful methods for working with regular expressions.
 */
@UtilityClass
public final class RegexUtils {

    /**
     * Check if string "aStr" match specified regular expression.
     * @param aStr - source string.
     * @param aRegex - regular expression.
     * @return - matcher if string match regex, or else null.
     */
    public static Matcher match(String aStr, String aRegex) {
        StringUtils.checkForNull(aStr, "aStr");
        StringUtils.checkForNull(aRegex, "aRegex");

        Matcher matcher = Pattern.compile(aRegex).matcher(aStr);
        if (matcher.find()) return matcher;
        else return null;
    }

}
