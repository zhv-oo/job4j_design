package ru.job4j.lsp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

interface Storage {
    boolean add(Food food);
    boolean accept(Food food);
    Integer getSize();

    /**
     * Метод получения процента остатка срока годности продукта.
     * Для тестов установлена дата на момент тестирования.
     * @param food проверяемый продукт.
     * @return процент остатка срока годности.
     */
    default double getExpirationPercent(Food food) {
        Calendar calendar = new GregorianCalendar(2021, Calendar.NOVEMBER, 22);
        Date dateToTest = calendar.getTime();
        return 100 - Math.toIntExact(
                (dateToTest.getTime() - food.getCreateDate().getTime())
                        * 100 / food.expiry());
    }
}