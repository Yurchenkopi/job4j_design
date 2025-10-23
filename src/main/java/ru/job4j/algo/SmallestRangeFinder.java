package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    /** Добавьте поля класса здесь, если это необходимо */

    public static int[] findSmallestRange(int[] nums, int k) {
        int[] result = new int[2];
        if (k == 1) {
            return result;
        }
        int pointer;
        boolean found = false;
        for (int i = 0; i < nums.length - k; i++) {
            pointer = i;
            for (int j = pointer; j < pointer + k; j++) {
                if ((nums[j + 1] - nums[j]) == 0) {
                    break;
                }
            }
            result[0] = pointer;
            result[1] = pointer + k;
            found = true;
            if (found) {
                break;
            }
        }
        if (!found) {
            result = null;
        }
        return result;
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
