package ru.job4j.exercise.map;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class URLFixer {

    public String fixQuery(String query, List<String> validParams) {
        Set<String> validParamSet = new HashSet<>(validParams);
        var rsl = new StringBuilder();
        var arr = query.split("&");
        for (String s : arr) {
            if (!rsl.isEmpty()) {
                rsl.append("&");
            }
            var sb = new StringBuilder();
            StringBuilder tempRsl = new StringBuilder();
            if(!isValid(s, validParamSet)) {
                for (int i = 0; i < s.length(); i++) {
                    sb.append(s.charAt(i));
                    if (validParamSet.contains(sb.toString())) {
                        tempRsl = new StringBuilder();
                        tempRsl.append(sb)
                                .append("=")
                                .append(s.substring(i + 1));
                    }
                }
            } else {
                sb.append(s);
            }
            if (tempRsl.isEmpty()) {
                rsl.append(sb);
            } else {
                rsl.append(tempRsl);
            }
        }
        return rsl.toString();
    }

    private boolean isValid(String query, Set<String> validParamSet) {
        var arr = query.split("=");
        return arr.length == 2 && validParamSet.contains(arr[0]);
    }

    public static void main(String[] args) {
        URLFixer fixer = new URLFixer();
        System.out.println(fixer.fixQuery("fooyes&barbaz&utm_sourcebar", Arrays.asList("foo", "bar", "utm_source")));
    }
}
