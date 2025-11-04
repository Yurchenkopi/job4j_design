package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return new int[] {-1, -1};
        }
        var activeIntervals = new PriorityQueue<Interval>(Comparator.comparingInt(i -> i.end));
        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;
        intervals.sort(Comparator.comparingInt(i -> i.start));
        for (Interval interval : intervals) {
            while (!activeIntervals.isEmpty() && activeIntervals.peek().end < interval.start) {
                activeIntervals.poll();
            }
            activeIntervals.offer(interval);
            int currentOverlap = activeIntervals.size();
            if (currentOverlap > maxOverlap) {
                maxOverlap = currentOverlap;
                maxStart = interval.start;
                maxEnd = activeIntervals.peek().end;
            }
        }
        return maxStart == maxEnd
                ? new int[] {maxStart - 1, maxEnd + 1}
                : new int[] {maxStart, maxEnd};
    }


    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}
