package ru.job4j.exercise.array;

import java.util.StringJoiner;

public class Task70 {
    public static void array(int[] arr) {
        StringJoiner sj1 = new StringJoiner(" ");
        for (int i = 0; i < (arr.length % 2 == 0 ? arr.length / 2 : arr.length / 2 + 1); i++) {
            sj1.add(String.valueOf(arr[i]));
        }
        StringJoiner sj2 = new StringJoiner(" ");
        for (int i =  arr.length % 2 == 0 ? arr.length / 2 : arr.length / 2 + 1; i < arr.length; i++) {
            sj2.add(String.valueOf(arr[i]));
        }
        System.out.println(sj1);
        System.out.println(sj2);
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 0, 3};
        Task70.array(arr);
    }
}
