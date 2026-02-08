package ru.job4j.exercise;

public class Task71 {
    public static void change(int money) {
        if (money % 10 != 0) {
            System.out.println("Нельзя");
            return;
        }
        int[] coins = new int[] {100, 50, 10};
        int[] array = new int[coins.length];
        int index = 0;
        while (money > 0) {
            if (money < coins[index]) {
                index++;
                continue;
            }
            money -= coins[index];
            array[index]++;
        }
        System.out.printf("100: %d, 50: %d, 10: %d%s", array[0], array[1], array[2], System.lineSeparator());
    }

    public static void main(String[] args) {
        change(156);
        char c = 5 + 48;
        System.out.println(c);
    }
}
