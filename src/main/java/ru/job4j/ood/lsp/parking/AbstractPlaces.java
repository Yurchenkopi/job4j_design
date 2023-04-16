package ru.job4j.ood.lsp.parking;

import java.util.*;
import java.util.function.Predicate;

public class AbstractPlaces implements ParkingService<Vehicle> {

    protected Map<Integer, Vehicle> places = new HashMap<>();

    public AbstractPlaces() {

    }

    @Override
    public void init() {
        for (int i = 1; i <= 100; i++) {
            places.put(i, null);
        }
    }

    @Override
    public boolean add(Vehicle vehicle) {
        boolean rsl = true;
        int number = findFreePlaces(1);
        if (number != -1) {
            places.put(number, vehicle);
        } else {
            System.out.println("No free parking places!");
            rsl = false;
        }
        return rsl;
    }

    /**
     * Метод позволяет разместить на парковке транспортное средство в случае выполнения условия condition
     * В случае успешного размещения на парковке транспортного средства возвращавется true
     */

    @Override
    public boolean add(Vehicle vehicle, Predicate<Vehicle> condition) {
        boolean rsl = false;
        if (condition.test(vehicle)) {
            rsl = add(vehicle);
        }
        return rsl;
    }

    @Override
    public void addAll(List<Vehicle> data, Predicate<Vehicle> condition) {
        for (Vehicle v : data) {
            add(v, condition);
        }
    }

    @Override
    public int findFreePlaces(int n) {
        int rsl = -1;
        Set<Integer> k = places.keySet();
        for (Integer number : k) {
            if (places.get(number) == null) {
                for (int i = 0; i < n; i++) {
                    if (places.get(number + i) == null && number + i <= k.size() && i == n - 1) {
                        rsl = number;
                        break;
                    }
                }
                if (rsl != -1) {
                    break;
                }
            }
        }
        return rsl;
    }

    @Override
    public List<Integer> findBy(Predicate<Vehicle> condition) {
        List<Integer> temp = new ArrayList<>();
        Set<Integer> k = places.keySet();
        for (Integer number : k) {
            if (places.get(number) != null && condition.test(places.get(number))) {
                temp.add(number);
            }
        }
        return temp;
    }

    @Override
    public List<Integer> findAllOccupied() {
        List<Integer> temp = new ArrayList<>();
        Set<Integer> k = places.keySet();
        for (Integer number : k) {
            if (places.get(number) != null) {
                temp.add(number);
            }
        }
        return temp;
    }

    @Override
    public void stop(Vehicle vehicle) {
        List<Integer> tempCar = findBy(v -> v.equals(vehicle));
        for (Integer number : tempCar) {
            places.put(number, null);
        }
    }
}
