package ru.job4j.exercise;

public class Task54 {
    public static void transform(int number) {

        int num1, num2, num3 = 0;
        num1 = number / 100;
        num2 = number / 10 % 10;
        num3 = number % 10;
        if (isEven(number)) {
            num1 = isEven(num1) ? increase(num1) : decrease(num1);
            num2 = isEven(num2) ? increase(num2) : decrease(num2);
            num3 = isEven(num3) ? increase(num3) : decrease(num3);
        } else {
            num1 = isEven(num1) ? decrease(num1) : increase(num1);
            num2 = isEven(num2) ? decrease(num2) : increase(num2);
            num3 = isEven(num3) ? decrease(num3) : increase(num3);
        }
        System.out.println(num1 * 100 + num2 * 10 + num3);
    }

    private static boolean isEven(int n) {
        return n % 2 == 0;
    }



    private static int increase(int n) {
        if (n < 9) {
            n++;
        }
        return n;
    }

    private static int decrease(int n) {
        if (n > 0) {
            n--;
        }
        return n;
    }
}
