package ru.job4j.exercise;

import java.util.ArrayList;

public class Task22 {
    public static void separate(int number) {
        var list = new ArrayList<Integer>();
        while (number > 0) {
            list.add(number % 10);
            number /= 10;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                System.out.print(list.get(i) + " ");
            } else {
                System.out.print(list.get(i) + System.lineSeparator());
            }
        }
    }

    public static void main(String[] args) {
        separate(23);
    }
}
