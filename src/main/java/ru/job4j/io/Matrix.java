package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class Matrix {
    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (i + 1) * (j + 1);
            }
        }
        return array;
    }

    public static void main(String[] args) {
        final int SIZE = 15;
        int[][] data = multiple(SIZE);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int[] array : data) {
                out.write(Arrays.toString(array).getBytes());
                out.write(System.lineSeparator().getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
