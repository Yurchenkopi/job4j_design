package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class TruckPlacesTest {

    @Test
    public void whenInitTruckPlaces() {
        int truckCapacity = 5;
        TruckPlaces places = new TruckPlaces(truckCapacity);
        Set<Integer> expected = Set.of(1, 2, 3, 4, 5);
        assertThat(places.getTruckPlaces().keySet()).isEqualTo(expected);
    }

    @Test
    public void whenAddCarToTruckPlacesThenResultFalse() {
        int truckCapacity = 5;
        TruckPlaces places = new TruckPlaces(truckCapacity);
        Vehicle vehicle = new Vehicle("p222pp777", CarSizes.S);
        assertThat(places.add(vehicle)).isFalse();
    }

    @Test
    public void whenAddCarToTruckPlacesAndNoFreePlacesThenResultFalse() {
        int truckCapacity = 1;
        TruckPlaces places = new TruckPlaces(truckCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.L);
        Vehicle v2 = new Vehicle("p222pp77", CarSizes.L);
        places.add(v1);
        assertThat(places.add(v2)).isFalse();
    }

    @Test
    public void whenAddCarToTruckPlacesThenResultTrue() {
        int truckCapacity = 5;
        TruckPlaces places = new TruckPlaces(truckCapacity);
        Vehicle vehicle = new Vehicle("p222pp777", CarSizes.L);
        assertThat(places.add(vehicle)).isTrue();
    }

    @Test
    public void whenAddAllTrucksThenCheckMap() {
        int truckCapacity = 5;
        TruckPlaces places = new TruckPlaces(truckCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6);
        places.addAll(vehicles, condition -> true);
        Map<Integer, Vehicle> expected = new HashMap<>();
        expected.put(1, v5);
        expected.put(2, v6);
        expected.put(3, null);
        expected.put(4, null);
        expected.put(5, null);
        assertThat(places.getTruckPlaces()).isEqualTo(expected);
    }

    @Test
    public void whenFindAllOccupiedAndUseForTrucksIsTrueTest() {
        int truckCapacity = 15;
        TruckPlaces places = new TruckPlaces(truckCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6);
        places.addAll(vehicles, condition -> true);
        int expected = 2;
        assertThat(places.findAllOccupied().size()).isEqualTo(expected);
    }

    @Test
    public void whenFindFreeNumberPlaceThenFindFreePlaces() {
        int truckCapacity = 15;
        TruckPlaces places = new TruckPlaces(truckCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.XL);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.S);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6);
        int freePlacesCount = 3;
        places.addAll(vehicles, condition -> true);
        int expected = 3;
        assertThat(places.findFreePlaces(freePlacesCount)).isEqualTo(expected);
    }

    @Test
    public void whenFindFreeNumberPlaceThenFindNoFreePlaces() {
        int truckCapacity = 5;
        TruckPlaces places = new TruckPlaces(truckCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6);
        int freePlacesCount = 4;
        places.addAll(vehicles, condition -> true);
        int expected = -1;
        assertThat(places.findFreePlaces(freePlacesCount)).isEqualTo(expected);
    }

    @Test
    public void whenFindByThenCheck() {
        int truckCapacity = 5;
        TruckPlaces places = new TruckPlaces(truckCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.L);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        Vehicle v7 = new Vehicle("m777mm77", CarSizes.M);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6, v7);
        places.addAll(vehicles, condition -> true);
        List<Vehicle> rsl = places.findBy(v -> CarSizes.L == v.getSize()).stream()
                .map(k -> places.getTruckPlaces().get(k))
                .toList();
        List<Vehicle> expected = List.of(v2, v5);
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    public void whenStopParkingThenCheckMap() {
        int truckCapacity = 5;
        TruckPlaces places = new TruckPlaces(truckCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.L);
        Vehicle v2 = new Vehicle("t435cc777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.L);
        List<Vehicle> vehicles = List.of(v1, v2, v3);
        places.addAll(vehicles, condition -> true);
        places.stop(v1);
        Map<Integer, Vehicle> expected = new HashMap<>();
        expected.put(1, null);
        expected.put(2, v3);
        expected.put(3, null);
        expected.put(4, null);
        expected.put(5, null);
        assertThat(places.getTruckPlaces()).isEqualTo(expected);
    }
}