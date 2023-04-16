package ru.job4j.ood.lsp.parking;

import java.util.List;

public class ParkingMeter {

    private final CarPlaces carParking;
    private final TruckPlaces truckParking;

    public ParkingMeter(int carCapacity, int truckCapacity) {
        this.carParking = new CarPlaces(carCapacity);
        this.truckParking = new TruckPlaces(truckCapacity);
    }

    public CarPlaces getCarParking() {
        return carParking;
    }

    public TruckPlaces getTruckParking() {
        return truckParking;
    }

    public void startParkingAll(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            startParking(v);
        }
    }

    public void startParking(Vehicle vehicle) {
        carParking.add(vehicle, condition -> !truckParking.add(vehicle));
    }

    public void finishParking(Vehicle vehicle) {
        getCarParking().stop(vehicle);
        getTruckParking().stop(vehicle);
    }
}
