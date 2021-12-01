package ru.job4j.lsp.parking;

public class Parking implements CarParking {
    private int carCell;
    private int truckCell;
    private int truckPos;
    private final Car[] cars;

    public Parking(int carCell, int truckCell) {
        this.carCell = carCell;
        this.truckCell = truckCell;
        this.truckPos = carCell + truckCell - 1;
        this.cars = new Car[carCell + truckCell];
    }

    @Override
    public boolean parkingCar(Car car) {
        boolean res = true;
        int carSize = car.getCarSize();
        if (carSize == 1 && carSize <= carCell) {
            cars[carCell - 1] = car;
            carCell--;
        } else if (carCell >= carSize && truckCell == 0) {
            cars[carCell - 1] = car;
            carCell -= carSize;
        } else if (truckCell != 0 && carSize > 1) {
            cars[truckPos--] = car;
            truckCell--;
        } else {
            res = false;
        }
        return res;
    }
}