package ru.job4j.lsp.parking;

public class Parking implements CarParking {
    private int carCell;
    private int truckCell;

    public Parking(int carCell, int truckCell) {
        this.carCell = carCell;
        this.truckCell = truckCell;
    }

    @Override
    public boolean parkingCar(Car car) {
        boolean res = true;
        int carSize = car.getCarSize();
        if (carSize == 1 && carSize <= carCell) {
            carCell--;
        } else if (carCell >= carSize && truckCell == 0) {
            carCell -= carSize;
        } else if (truckCell != 0 && carSize > 1) {
            truckCell--;
        } else {
            res = false;
        }
        return res;
    }
}