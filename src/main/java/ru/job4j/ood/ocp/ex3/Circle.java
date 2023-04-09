package ru.job4j.ood.ocp.ex3;

/*
Rectangle и Circle имеют метод getArea(), который возвращает площадь фигуры.
Если мы хотим добавить новую фигуру, например, треугольник, то нам придётся
изменить существующий код и добавить новый класс. Это нарушает принцип OCP.
Для того чтобы такой ситуации не возникло, требовалось использовать абстракцию.
 */

public class Circle {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public double getArea() {
        return 3.14 * radius * radius;
    }
}