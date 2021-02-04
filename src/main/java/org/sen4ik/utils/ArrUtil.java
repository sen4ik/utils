package org.sen4ik.utils;

import com.google.common.collect.Ordering;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ArrUtil {

    public static List<String> arrayToUniqueList(ArrayList<String> arr){
        Set<String> s = new HashSet<>(arr);
        return new ArrayList<>(s);
    }

    public static boolean isArraySortedAlphabetically(ArrayList<String> l){
        return Ordering.from(String.CASE_INSENSITIVE_ORDER).isOrdered(l);
    }

    public static boolean doesArrayContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
}
