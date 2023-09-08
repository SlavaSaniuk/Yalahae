package me.saniukvyacheslav.util.list;


import java.util.List;
import java.util.Objects;

public final class ListUtils {

    public static <T> String toString(List<T> aList) {
        // Check parameters:
        Objects.requireNonNull(aList, "List [aList] must be not null.");
        if (aList.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        aList.forEach((elem) -> {
            if (ListUtils.isLastElement(elem, aList)) sb.append(String.format("%s]", elem.toString()));
            else sb.append(String.format("%s, ", elem.toString()));
        });

        return sb.toString();
    }

    public static <T> boolean isLastElement(T anElement, List<T> aList) {
        if (aList == null || aList.isEmpty()) return false;

        T lastElem = aList.get(aList.size() -1);
        return lastElem == anElement;
    }

    /*
    public static <T> T getLast(@Nullable List<T> aList) {
        if (aList != null && !aList.isEmpty()) {
            return aList.get(aList.size() - 1);
        }
        return null;
    }
     */
}
