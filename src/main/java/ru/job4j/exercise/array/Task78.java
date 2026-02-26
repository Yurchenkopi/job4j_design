package ru.job4j.exercise.array;

import java.util.*;

public class Task78 {
    public static void array(int[] nums) {
        var sj = new StringJoiner(" ");
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (Integer i : nums) {
            map.computeIfPresent(i, (key, value) -> value + 1);
            map.putIfAbsent(i, 1);
        }
        for (Integer i : map.keySet()) {
            if (map.get(i) == 1) {
                sj.add(String.valueOf(i));
            }
        }
        System.out.printf(sj.toString().isEmpty() ? "%s" : "%s%s", sj, sj.toString().isEmpty() ? "" : System.lineSeparator());
    }

    public static void main(String[] args) {
        int[] nums = {5, 5, 4, 3, 3, 1};
        array(nums);
    }
}
