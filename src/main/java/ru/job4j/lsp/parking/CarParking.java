package ru.job4j.lsp.parking;

/**
 * \* User: zhv
 * \* Date: 22.11.2021
 * \* Time: 13:11
 * \* Description: Интерфейс для реализации механизма постановки на парковку.
 * \
 */
interface CarParking {
    boolean parkingCar(Car car);
}