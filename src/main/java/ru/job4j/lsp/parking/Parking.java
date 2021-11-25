package ru.job4j.lsp.parking;

public class Parking implements CarParking {
    private final int carCell;
    private final int truckCell;

    public Parking(int carCell, int truckCell) {
        this.carCell = carCell;
        this.truckCell = truckCell;
    }

    @Override
    public boolean parkingCar(Car car) {
        boolean res;
        int carSize = car.getCarSize();
        if (carSize == 1) {
            res = carCell >= carSize;
        } else {
            res = truckCell >= carSize;
        }
        return res;
    }
}