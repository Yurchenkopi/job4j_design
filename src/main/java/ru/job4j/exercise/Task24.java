package ru.job4j.exercise;

import java.util.ArrayList;

public class Task24 {
    public static void separate(int number) {
        var list = new ArrayList<Integer>();
        int multiply = 1;
        while (number > 0) {
            list.add(number % 10 * multiply);
            number /= 10;
            multiply *= 10;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                System.out.print(list.get(size - 1 - i) + "+");
            } else {
                System.out.print(list.get(size - 1 - i) + System.lineSeparator());
            }
        }
    }

    public static void main(String[] args) {
        separate(23);
    }
}
