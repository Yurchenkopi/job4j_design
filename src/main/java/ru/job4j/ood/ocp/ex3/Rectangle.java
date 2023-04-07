package ru.job4j.ood.ocp.ex3;

/*
Rectangle и Circle имеют метод getArea(), который возвращает площадь фигуры.
Если мы хотим добавить новую фигуру, например, треугольник, то нам придётся
изменить существующий код и добавить новый класс. Это нарушает принцип OCP.
Для того чтобы такой ситуации не возникло, требовалось использовать абстракцию.
 */

public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}
