package ru.job4j.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarParkingTest {
    @Test
    public void whenCanNotPassCarPark() {
        CarParking carParking = new Parking();
        Car passCar = new PassCar();
        assertFalse(carParking.parkingCar(passCar));
    }

    @Test
    public void whenCanNotTruckPark() {
        CarParking carParking = new Parking();
        Car truck = new Truck();
        assertFalse(carParking.parkingCar(truck));
    }
}