package ru.job4j.ood.lsp.ex2;

public class Ostrich extends Bird {

    public void fly(int altitude) {
        if (altitude > 0) {
            throw new IllegalArgumentException("Ostrich can't fly!");
        }
        System.out.println("Flight altitude is " + altitude);
    }

    /*
    Изменение постусловия, а именно добавление исключение в работе метода
     */

    public static void main(String[] args) {
        Bird bird = new Ostrich();
        bird.fly(500);
    }
}
