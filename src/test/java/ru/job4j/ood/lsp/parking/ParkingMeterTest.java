package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ParkingMeterTest {

    @Test
    public void whenStartParkingAllWithoutUsingForTrucksCarParkingThenCheckVehicles() {
        int carCapacity = 5;
        int truckCapacity = 5;
        ParkingMeter parking = new ParkingMeter(carCapacity, truckCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.M);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.M);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.S);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6);
        parking.startParkingAll(vehicles);
        Map<Integer, Vehicle> expectedCar = new HashMap<>();
        expectedCar.put(1, v1);
        expectedCar.put(2, v2);
        expectedCar.put(3, v3);
        expectedCar.put(4, v4);
        expectedCar.put(5, null);
        Map<Integer, Vehicle> expectedTruck = new HashMap<>();
        expectedTruck.put(1, v5);
        expectedTruck.put(2, v6);
        expectedTruck.put(3, null);
        expectedTruck.put(4, null);
        expectedTruck.put(5, null);
        assertThat(parking.getCarParking().getCarPlaces()).isEqualTo(expectedCar);
        assertThat(parking.getTruckParking().getTruckPlaces()).isEqualTo(expectedTruck);
    }

    @Test
    public void whenStartParkingAllWithUsingForTrucksCarParkingThenCheckVehicles() {
        int carCapacity = 5;
        int truckCapacity = 5;
        ParkingMeter parking = new ParkingMeter(carCapacity, truckCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.XL);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.L);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.L);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        Vehicle v7 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6, v7);
        parking.startParkingAll(vehicles);
        Map<Integer, Vehicle> expectedCar = new HashMap<>();
        expectedCar.put(1, v1);
        expectedCar.put(2, v7);
        expectedCar.put(3, v7);
        expectedCar.put(4, v7);
        expectedCar.put(5, v7);
        Map<Integer, Vehicle> expectedTruck = new HashMap<>();
        expectedTruck.put(1, v2);
        expectedTruck.put(2, v3);
        expectedTruck.put(3, v4);
        expectedTruck.put(4, v5);
        expectedTruck.put(5, v6);
        assertThat(parking.getCarParking().getCarPlaces()).isEqualTo(expectedCar);
        assertThat(parking.getTruckParking().getTruckPlaces()).isEqualTo(expectedTruck);
    }

    @Test
    public void whenFinishParkingWithUsingForTrucksCarParkingThenCheckVehicles() {
        int carCapacity = 5;
        int truckCapacity = 5;
        ParkingMeter parking = new ParkingMeter(carCapacity, truckCapacity);
        Vehicle v1 = new Vehicle("p222pp777", CarSizes.S);
        Vehicle v2 = new Vehicle("o333oo777", CarSizes.XL);
        Vehicle v3 = new Vehicle("m777mm777", CarSizes.L);
        Vehicle v4 = new Vehicle("c555cc777", CarSizes.L);
        Vehicle v5 = new Vehicle("t435cc777", CarSizes.L);
        Vehicle v6 = new Vehicle("f344cc777", CarSizes.XL);
        Vehicle v7 = new Vehicle("f344cc777", CarSizes.XL);
        List<Vehicle> vehicles = List.of(v1, v2, v3, v4, v5, v6, v7);
        parking.startParkingAll(vehicles);
        parking.finishParking(v7);
        Map<Integer, Vehicle> expectedCar = new HashMap<>();
        expectedCar.put(1, v1);
        expectedCar.put(2, null);
        expectedCar.put(3, null);
        expectedCar.put(4, null);
        expectedCar.put(5, null);
        Map<Integer, Vehicle> expectedTruck = new HashMap<>();
        expectedTruck.put(1, v2);
        expectedTruck.put(2, v3);
        expectedTruck.put(3, v4);
        expectedTruck.put(4, v5);
        expectedTruck.put(5, v6);
        assertThat(parking.getCarParking().getCarPlaces()).isEqualTo(expectedCar);
        assertThat(parking.getTruckParking().getTruckPlaces()).isEqualTo(expectedTruck);
    }
}