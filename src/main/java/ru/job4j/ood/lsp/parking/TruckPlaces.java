package ru.job4j.ood.lsp.parking;

import java.util.Map;

public class TruckPlaces extends AbstractPlaces {
    private final int truckParkingCapacity;

    public TruckPlaces(int truckParkingCapacity) {
        super();
        this.truckParkingCapacity = truckParkingCapacity;
        init();
    }

    public Map<Integer, Vehicle> getTruckPlaces() {
        return super.places;
    }

    @Override
    public void init() {
        for (int i = 1; i <= truckParkingCapacity; i++) {
            places.put(i, null);
        }
    }

    @Override
    public boolean add(Vehicle vehicle) {
        boolean rsl = true;
        CarSizes size = vehicle.getSize();
        int truckNumber = findFreePlaces(1);
        int carNumber = findFreePlaces(size.ordinal() + 1);
        if (size.ordinal() == 2 || size.ordinal() == 3) {
            if (truckNumber != -1) {
                super.places.put(truckNumber, vehicle);
            } else if (carNumber != -1) {
                for (int i = 0; i <= size.ordinal(); i++) {
                    super.places.put(carNumber + i, vehicle);
                }
            } else {
                rsl = false;
            }
        } else {
            rsl = false;
        }
        return  rsl;
    }
}