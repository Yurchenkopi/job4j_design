package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        boolean found = false;
        int[] result = new int[2];
        if (k == 1) {
            found = true;
        }
        int pointer = 0;
        while (!found && pointer <= nums.length - k) {
            for (int j = pointer; j < pointer + k - 1; j++) {
                if (nums[j + 1] - nums[j] == 0) {
                    pointer = j + 1;
                    break;
                }
                if (j == pointer + k - 2) {
                    result[0] = pointer;
                    result[1] = pointer + k - 1;
                    found = true;
                }
            }
        }
        return found ? result : null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
