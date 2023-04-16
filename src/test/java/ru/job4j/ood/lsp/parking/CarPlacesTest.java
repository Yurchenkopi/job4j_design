package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class CarPlacesTest {

    @Test
    public void whenInitCarPlaces() {
        int carCapacity = 5;
        CarPlaces places = new CarPlaces(carCapacity);
        Set<Integer> expected = Set.of(1, 2, 3, 4, 5);
        assertThat(places.getCarPlaces().keySet()).isEqualTo(expected);
    }

    @Test
    public void whenAddTrucksToCarPlacesWithUsualAddMethodAndResultFalse() {
        int carCapacity = 5;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle vehicle = new Vehicle("p222pp777", CarSizes.L);
        assertThat(places.add(vehicle)).isFalse();
    }

    @Test
    public void whenAddCarToCarPlacesAndNoFreePlacesThenResultFalse() {
        int carCapacity = 1;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("p222pp77", CarSizes.M);
        places.add(v1);
        assertThat(places.add(v2)).isFalse();
    }

    @Test
    public void whenAddCarToCarPlacesWithUsualAddMethodAndResultTrue() {
        int carCapacity = 5;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle vehicle = new Vehicle("p222pp777", CarSizes.S);
        assertThat(places.add(vehicle)).isTrue();
    }

    @Test
    public void whenAddCarToCarPlacesWithExtendedAddMethodAndUseForTrucksIsTrueThenResultTrue() {
        int carCapacity = 5;
        boolean useForTrucks = true;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle vehicle = new Vehicle("p222pp777", CarSizes.S);
        assertThat(places.add(vehicle, condition -> useForTrucks)).isTrue();
    }

    @Test
    public void whenAddCarToCarPlacesWithExtendedAddMethodAndUseForTrucksIsFalseThenResultTrue() {
        int carCapacity = 5;
        boolean useForTrucks = false;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle vehicle = new Vehicle("p222pp777", CarSizes.S);
        assertThat(places.add(vehicle, condition -> useForTrucks)).isTrue();
    }

    @Test
    public void whenAddTrucksToCarPlacesWithExtendedAddMethodAndUseForTrucksIsTrueThenResultTrue() {
        int carCapacity = 5;
        boolean useForTrucks = true;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle vehicle = new Vehicle("p222pp777", CarSizes.L);
        assertThat(places.add(vehicle, condition -> useForTrucks)).isTrue();
    }

    @Test
    public void whenAddTrucksToCarPlacesWithExtendedAddMethodAndUseForTrucksIsFalseThenResultFalse() {
        int carCapacity = 5;
        boolean useForTrucks = false;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle vehicle = new Vehicle("p222pp777", CarSizes.L);
        assertThat(places.add(vehicle, condition -> useForTrucks)).isFalse();
    }

    @Test
    public void whenAddAllCarsToCarPlacesAndDontUseForTrucksThenCheckMap() {
        int carCapacity = 5;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6);
        boolean useForTrucks = false;
        places.addAll(vehicles, condition -> useForTrucks);
        Map<Integer, Vehicle> expected = new HashMap<>();
        expected.put(1, v1);
        expected.put(2, v2);
        expected.put(3, v3);
        expected.put(4, v4);
        expected.put(5, null);
        assertThat(places.getCarPlaces()).isEqualTo(expected);
    }

    @Test
    public void whenAddAllCarsToCarPlacesAndUseForTrucksThenCheckMap() {
        int carCapacity = 5;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("t435cc777", CarSizes.L);
        List<Vehicle> vehicles = List.of(v1, v2);
        boolean useForTrucks = true;
        places.addAll(vehicles, condition -> useForTrucks);
        Map<Integer, Vehicle> expected = new HashMap<>();
        expected.put(1, v1);
        expected.put(2, v2);
        expected.put(3, v2);
        expected.put(4, v2);
        expected.put(5, null);
        assertThat(places.getCarPlaces()).isEqualTo(expected);
    }

    @Test
    public void whenFindAllOccupiedAndUseForTrucksIsTrueTest() {
        int carCapacity = 15;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6);
        boolean useForTrucks = true;
        places.addAll(vehicles, condition -> useForTrucks);
        int expected = 11;
        assertThat(places.findAllOccupied().size()).isEqualTo(expected);
    }

    @Test
    public void whenFindAllOccupiedAndUseForTrucksIsFalseTest() {
        int carCapacity = 15;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6);
        boolean useForTrucks = false;
        places.addAll(vehicles, condition -> useForTrucks);
        int expected = 4;
        assertThat(places.findAllOccupied().size()).isEqualTo(expected);
    }

    @Test
    public void whenFindFreeNumberPlaceAndUseForTrucksIsTrueThenFindFreePlaces() {
        int carCapacity = 15;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6);
        boolean useForTrucks = true;
        int freePlacesCount = 4;
        places.addAll(vehicles, condition -> useForTrucks);
        int expected = 12;
        assertThat(places.findFreePlaces(freePlacesCount)).isEqualTo(expected);
    }

    @Test
    public void whenFindFreeNumberPlaceAndUseForTrucksIsTrueThenFindNoFreePlaces() {
        int carCapacity = 15;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6);
        boolean useForTrucks = true;
        int freePlacesCount = 5;
        places.addAll(vehicles, condition -> useForTrucks);
        int expected = -1;
        assertThat(places.findFreePlaces(freePlacesCount)).isEqualTo(expected);
    }

    @Test
    public void whenFindByAndUseForTrucksIsTrueThenCheck() {
        int carCapacity = 15;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        Vehicle v7 = new Vehicle("m777mm77", CarSizes.M);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6, v7);
        boolean useForTrucks = true;
        places.addAll(vehicles, condition -> useForTrucks);
        List<Vehicle> rsl = places.findBy(v -> CarSizes.M == v.getSize()).stream()
                        .map(k -> places.getCarPlaces().get(k))
                                .toList();
        List<Vehicle> expected = List.of(v2, v3, v7);
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    public void whenStopParkingAndUseForTrucksIsFalseThenCheckMap() {
        int carCapacity = 5;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("t435cc777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.L);
        List<Vehicle> vehicles = List.of(v1, v2, v3);
        boolean useForTrucks = false;
        places.addAll(vehicles, condition -> useForTrucks);
        places.stop(v1);
        Map<Integer, Vehicle> expected = new HashMap<>();
        expected.put(1, null);
        expected.put(2, v2);
        expected.put(3, null);
        expected.put(4, null);
        expected.put(5, null);
        assertThat(places.getCarPlaces()).isEqualTo(expected);
    }

    @Test
    public void whenStopParkingCarAndUseForTrucksIsTrueThenCheckMap() {
        int carCapacity = 5;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("t435cc777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.L);
        List<Vehicle> vehicles = List.of(v1, v2, v3);
        boolean useForTrucks = true;
        places.addAll(vehicles, condition -> useForTrucks);
        places.stop(v1);
        Map<Integer, Vehicle> expected = new HashMap<>();
        expected.put(1, null);
        expected.put(2, v2);
        expected.put(3, v3);
        expected.put(4, v3);
        expected.put(5, v3);
        assertThat(places.getCarPlaces()).isEqualTo(expected);
    }

    @Test
    public void whenStopParkingTruckAndUseForTrucksIsTrueThenCheckMap() {
        int carCapacity = 5;
        CarPlaces places = new CarPlaces(carCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("t435cc777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.L);
        List<Vehicle> vehicles = List.of(v1, v2, v3);
        boolean useForTrucks = true;
        places.addAll(vehicles, condition -> useForTrucks);
        places.stop(v3);
        Map<Integer, Vehicle> expected = new HashMap<>();
        expected.put(1, v1);
        expected.put(2, v2);
        expected.put(3, null);
        expected.put(4, null);
        expected.put(5, null);
        assertThat(places.getCarPlaces()).isEqualTo(expected);
    }
}