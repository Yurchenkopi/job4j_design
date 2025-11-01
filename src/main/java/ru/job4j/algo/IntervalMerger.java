package ru.job4j.algo;

import java.util.Arrays;

public class IntervalMerger {
    public int[][] merge(int[][] intervals) {
        int[][] rsl;
        int[][] sortedIntervals = sort(intervals);
        rsl = mergeIntervals(sortedIntervals[0], sortedIntervals[1]);
        int i = 2;
        while (i < sortedIntervals.length) {
            var mergedInterval = mergeIntervals(rsl[rsl.length - 1], sortedIntervals[i]);
            int[][] temp = new int[rsl.length + mergedInterval.length - 1][];
            System.arraycopy(rsl, 0, temp, 0, rsl.length);
            System.arraycopy(mergedInterval, 0, temp, rsl.length - 1, mergedInterval.length);
            rsl = Arrays.copyOf(temp, temp.length);
            i++;
        }
        return rsl;
    }

    private static int[][] mergeIntervals(int[] a, int[] b) {
        return b[0] <= a[1] ? new int[][]{{a[0], b[1]}} : new int[][]{a, b};
    }

    private static int[][] sort(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        int[][] left = Arrays.copyOfRange(intervals, 0, intervals.length / 2);
        int[][] right = Arrays.copyOfRange(intervals, left.length, intervals.length);
        return merge(sort(left), sort(right));
    }

    private static int[][] merge(int[][] a, int[][] b) {
        int i = 0, j = 0, k = 0;
        int[][] rsl = new int[a.length + b.length][2];
        while (i < a.length && j < b.length) {
            rsl[k++] = a[i][0] < b[j][0] ? a[i++] : b[j++];
        }
        if (i < a.length) {
            System.arraycopy(a, i, rsl, k, a.length - i);
        } else if (j < b.length) {
            System.arraycopy(b, j, rsl, k, b.length - j);
        }
        return rsl;
    }
}
