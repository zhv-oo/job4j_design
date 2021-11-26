package ru.job4j.isp;

/**
 * Пример нарушения принципа ISP.
 * Не все автомобили имеют режим offRoad.
 */
public interface Car {
    void drive();
    void stop();
    void offRoad();
}