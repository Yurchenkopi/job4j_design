package ru.job4j.ood.ocp.ex2;

/*
Kласс Animal определяет общий интерфейс для всех животных, который включает метод makeSound().
Однако, Duck имеет метод quack(), который не является частью общего интерфейса.
При добавлении нового класса, который имеет другой метод, необходимо изменить интерфейс Animal
и добавить новый метод, а это нарушит принцип OCP.
 */

public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract void makeSound();
}