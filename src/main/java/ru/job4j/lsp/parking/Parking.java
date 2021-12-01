package ru.job4j.lsp.parking;

public class Parking implements CarParking {
    private final Car[] cars;
    private int carCell;
    private int truckCell;
    private int passCount = 0;
    private int truckCount = 0;

    public Parking(int carCell, int truckCell) {
        this.carCell = carCell;
        this.truckCell = truckCell;
        this.cars = new Car[carCell + truckCell];
    }

    @Override
    public boolean parkingCar(Car car) {
        boolean res = true;
        int carSize = car.getCarSize();
        if (carSize == 1 && passCount < carCell) {
            cars[passCount++] = car;
        } else if (carCell - passCount >= carSize && truckCount == truckCell) {
            int i = 0;
            while (i != carSize) {
                cars[passCount++] = car;
                i++;
            }
        } else if (truckCount != truckCell && carSize > 1) {
            cars[truckCount++] = car;
        } else {
            res = false;
        }
        return res;
    }

    @Override
    public Car getCar(Integer pos) {
        return cars[pos];
    }
}