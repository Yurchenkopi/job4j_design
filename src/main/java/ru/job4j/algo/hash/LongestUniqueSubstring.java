package ru.job4j.algo.hash;

import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        StringBuilder rsl = new StringBuilder();
        int longestStrLength = 0;
        int i = 0;
        while (i < str.length() - longestStrLength) {
            int uniqueSubstringLength = findNextUniqueSubstringLength(str.substring(i));
            if (uniqueSubstringLength > longestStrLength) {
                rsl = new StringBuilder(str.substring(i, i + uniqueSubstringLength));
                longestStrLength = uniqueSubstringLength;
            }
            i++;
        }
        return rsl.toString();
    }

    private static boolean addAndCheckIfTheEndOfUniqueSubstring(Set<Character> set, Character ch) {
        boolean rsl = false;
        int currentSize = set.size();
        set.add(ch);
        if (set.size() == currentSize) {
            rsl = true;
        }
        return rsl;
    }

    private static int findNextUniqueSubstringLength(String str) {
        Set<Character> set = new HashSet<>();
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (!addAndCheckIfTheEndOfUniqueSubstring(set, str.charAt(i))) {
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }
}
