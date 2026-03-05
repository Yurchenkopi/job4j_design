package ru.job4j.exercise.map;

import java.util.*;

public class AnalyzeHttpLog {
    public record Line(String level, String thread, String text) {}


    public static Map<String, Long> groupByLevel(List<Line> logs) {
        var map = new HashMap<String, Long>();
        for (Line l : logs) {
            map.computeIfPresent(l.level(), (k, v) -> v + 1);
            map.putIfAbsent(l.level(), 1l);
        }
        return map;
    }

    public static String maxByThread(List<Line> logs) {
        var map = new HashMap<String, Long>();
        for (Line l : logs) {
            map.computeIfPresent(l.thread(), (k, v) -> v + 1);
            map.putIfAbsent(l.thread(), 1L);
        }
        long maxVal = 0;
        String rsl ="";
        for (String key : map.keySet()) {
            if (map.get(key) > maxVal) {
                maxVal = map.get(key);
                rsl = key;
            }
        }
        return rsl;
    }

    public static Map<String, Long> detailByThread(List<Line> logs, String thread,
                                                   List<String> orders) {
        var filteredMap = new HashMap<String, Long>();
        for (Line l : logs) {
            if (l.thread().equals(thread)) {
                filteredMap.computeIfPresent(l.level(), (k, v) -> v + 1);
                filteredMap.putIfAbsent(l.level, 1L);
            }
        }
        var rsl = new HashMap<String, Long>();
        for (String order : orders) {
            rsl.put(order, filteredMap.get(order));
        }
        return rsl;
    }
}
