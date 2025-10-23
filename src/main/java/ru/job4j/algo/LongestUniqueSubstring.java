package ru.job4j.algo;

import java.util.*;
import java.util.stream.Collectors;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        Map<Integer, Set<Character>> data = new HashMap<>();
        var temp = new HashSet<Character>();
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            int size = temp.size();
            temp.add(str.charAt(i));
            if (temp.size() == size || i == str.length() - 1) {
                data.put(index, temp);
                index++;
                temp = new HashSet<>();
                temp.add(str.charAt(i));
            }
        }
        return str.length() == 0 ? str : findMaxLength(data);
    }

    public static String longestUnSubstr(String str) {
        String s = grabUniqueString(str);
        for (int i = 0; i < str.length(); i++) {

        }
        return "";
    }

    public static String grabUniqueString(String str) {
        var temp = new HashSet<Character>();
        int size = 0;
        for (int i = 0; i < str.length(); i++) {
            temp.add(str.charAt(i));
            if (temp.size() == size) {
                break;
            }
            size = temp.size();
        }
        var sb = new StringBuilder();
        for (Character ch : temp) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String findMaxLength(Map<Integer, Set<Character>> map) {
        int size = 0;
        int index = 0;
        for (Integer i : map.keySet()) {
            var temp = map.get(i).size();
            if (temp > size) {
                size = temp;
                index = i;
            }
        }
        var sb = new StringBuilder();
        for (Character ch : map.get(index)) {
            sb.append(ch);
        }
        return sb.toString();
    }
}
