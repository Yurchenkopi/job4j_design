package ru.job4j.ood.lsp.ex1;

public class Square extends Rectangle {

    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }

    /*
    Изменение предусловий в подклассе
    Без переопределения сеттера setWeight(), где мы одновременно обновляем ширину с высотой,
    при создании квадрата и повторном присваивании новой ширины будет создан прямоугольник.
    Такое поведение является следствием усиления предусловий в подклассе Square
     */

    public static void main(String[] args) {
        Rectangle square = new Square();
        square.setHeight(5);
        square.setWidth(7);
        System.out.println(square.getWidth() + " ; " + square.getHeight());
    }
}
