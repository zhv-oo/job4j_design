package ru.job4j.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarParkingTest {
    @Test
    public void whenCanPassCarPark() {
        Parking carParking = new Parking(1, 1);
        Car passCar = new PassCar();
        assertTrue(carParking.parkingCar(passCar));
    }

    @Test
    public void whenCanTruckPark() {
        CarParking carParking = new Parking(1, 1);
        Car passCar = new PassCar();
        Car truck = new Truck(3);
        carParking.parkingCar(passCar);
        assertTrue(carParking.parkingCar(truck));
    }

    @Test
    public void whenCanTruckParkInPass() {
        CarParking carParking = new Parking(6, 0);
        Car truck = new Truck(4);
        carParking.parkingCar(new PassCar());
        carParking.parkingCar(new PassCar());
        assertTrue(carParking.parkingCar(truck));
        assertFalse(carParking.parkingCar(truck));
    }

    @Test
    public void whenCanTPassCarPark() {
        Parking carParking = new Parking(0, 3);
        Car passCar = new PassCar();
        assertFalse(carParking.parkingCar(passCar));
    }

    @Test
    public void whenCanTTruckPark() {
        CarParking carParking = new Parking(1, 0);
        Car truck = new Truck(3);
        assertFalse(carParking.parkingCar(truck));
    }
}