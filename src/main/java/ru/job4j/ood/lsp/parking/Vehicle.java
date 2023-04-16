package ru.job4j.ood.lsp.parking;

public class Vehicle {
    private final String regNumber;
    private final CarSizes size;

    public Vehicle(String regNumber, CarSizes size) {
        this.regNumber = regNumber;
        this.size = size;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public CarSizes getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Vehicle{"
                + "regNumber='" + regNumber + '\''
                + ", size=" + size
                + '}';
    }
}
