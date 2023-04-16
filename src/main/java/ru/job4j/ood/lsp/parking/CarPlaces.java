package ru.job4j.ood.lsp.parking;

import java.util.Map;
import java.util.function.Predicate;

public class CarPlaces extends AbstractPlaces {
    private final int carParkingCapacity;

    public CarPlaces(int carParkingCapacity) {
        super();
        this.carParkingCapacity = carParkingCapacity;
        init();
    }

    public Map<Integer, Vehicle> getCarPlaces() {
        return super.places;
    }

    @Override
    public void init() {
        for (int i = 1; i <= carParkingCapacity; i++) {
            places.put(i, null);
        }
    }

    @Override
    public boolean add(Vehicle vehicle) {
        boolean rsl = true;
        CarSizes size = vehicle.getSize();
        if (size.ordinal() == 0 || size.ordinal() == 1) {
            int number = findFreePlaces(1);
            if (number != -1) {
                super.places.put(number, vehicle);
            } else {
                System.out.println("No free parking place for " + vehicle.getRegNumber() + " " + vehicle.getSize());
                rsl = false;
            }
        } else {
            rsl = false;
        }
        return rsl;
    }

    @Override
    public boolean add(Vehicle vehicle, Predicate<Vehicle> useForTrucks) {
        boolean rsl = false;
        CarSizes size = vehicle.getSize();
        if (useForTrucks.test(vehicle)) {
            int number = findFreePlaces(size.ordinal() + 1);
            if (size.ordinal() == 2 || size.ordinal() == 3) {
                if (number != -1) {
                    for (int i = 0; i <= size.ordinal(); i++) {
                        super.places.put(number + i, vehicle);
                    }
                    rsl = true;
                } else {
                    System.out.println("No free parking place for " + vehicle.getRegNumber() + " " + vehicle.getSize());
                }
            }
            if (size.ordinal() == 0 || size.ordinal() == 1) {
                rsl = add(vehicle);
            }
        } else {
            rsl = add(vehicle);
        }
        return rsl;
    }
}
