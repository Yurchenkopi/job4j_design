package ru.job4j.ood.ocp.ex2;

public class Duck extends Animal {
    public Duck(String name) {
        super(name);
    }

    public void quack() {
        System.out.println("Quack!");
    }

    @Override
    public void makeSound() {
        quack();
    }
}