package org.sen4ik.utilities;

import java.util.*;

public class ArrayUtility {

	public static List<String> arrayToUniqueList(ArrayList<String> arr) {
		Set<String> s = new HashSet<>(arr);
		return new ArrayList<>(s);
	}

	public static boolean isArraySortedAlphabetically(ArrayList<String> l) {
		List<String> listSorted = new ArrayList<String>(l);
		Collections.sort(listSorted);
		return listSorted.equals(l);
	}

	public static boolean doesArrayContains(String[] arr, String targetValue) {
		return Arrays.asList(arr).contains(targetValue);
	}

}