package ru.job4j.ood.ocp.ex1;

public class Vehicle {
    private String type;

    public Vehicle(String type) {
        this.type = type;
    }

    /*
    При добавлении нового типа транспорта необходимо изменять метод move(),
    что противоречит принципу OCP о неизменяемости кода
     */

    public void move(int distance) {
        if (type.equals("car")) {
            System.out.println("The car moved " + distance + " km");
        } else if (type.equals("truck")) {
            System.out.println("The truck moved " + distance + " km");
        }
    }
}
