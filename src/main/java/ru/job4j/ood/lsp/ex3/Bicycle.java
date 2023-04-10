package ru.job4j.ood.lsp.ex3;

public class Bicycle extends Vehicle {

    public void drive(int distanceInMeters) {
        System.out.println("Distance in meters " + distanceInMeters);
    }

    /*
    Нарушение условий базового класса (нарушение инвариант класса Vehicle)
     */

    public static void main(String[] args) {
        Vehicle vehicle = new Bicycle();
        vehicle.drive(1000);
    }
}
