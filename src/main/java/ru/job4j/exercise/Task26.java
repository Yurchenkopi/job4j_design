package ru.job4j.exercise;

public class Task26 {
    public static void transform(int number) {
        var sb = new StringBuilder();
        while (number > 0) {
            sb.append(number % 10);
            number /= 10;
        }
        System.out.println(Integer.parseInt(sb.toString()) + 8);
    }

    public static void main(String[] args) {
        transform(23);
    }
}
