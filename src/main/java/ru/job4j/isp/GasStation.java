package ru.job4j.isp;

/**
 * Пример нарушения принципа ISP.
 * Не на всех заправочных станциях может быть заправка газом.
 */
public interface GasStation {
    void fillGasoline();
    void fillDiesel();
    void fillGas();
}