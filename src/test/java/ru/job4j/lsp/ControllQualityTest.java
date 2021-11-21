package ru.job4j.lsp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ControllQualityTest {
    @Test
    public void whenAllInWarehouse() {
        ControllQuality controllQuality = new ControllQuality();
        Calendar calendar = new GregorianCalendar(2021, Calendar.JANUARY, 0);
        Date createDate = calendar.getTime();
        calendar.set(2021, Calendar.MARCH, 0);
        Date expiryDate = calendar.getTime();
        calendar.set(2021, Calendar.JANUARY, 15);
        Date timeLeft = calendar.getTime();
        Food cheese = new Cheese("Сыр", expiryDate, createDate, 150.0, 0.0);
        Food cheeseTwo = new Cheese("Сыр", expiryDate, createDate, 150.0, 0.0);
        Food cheeseThree = new Cheese("Сыр", expiryDate, createDate, 150.0, 0.0);
        controllQuality.checkFood(cheese, timeLeft);
        controllQuality.checkFood(cheeseTwo, timeLeft);
        controllQuality.checkFood(cheeseThree, timeLeft);
        assertEquals(controllQuality.sizeWarehouse(), Integer.valueOf(3));
        assertEquals(controllQuality.sizeShop(), Integer.valueOf(0));
        assertEquals(controllQuality.sizeTrash(), Integer.valueOf(0));
    }

    @Test
    public void whenAllInShop() {
        ControllQuality controllQuality = new ControllQuality();
        Calendar calendar = new GregorianCalendar(2021, Calendar.MARCH, 0);
        Date createDate = calendar.getTime();
        calendar.set(2021, Calendar.MARCH, 15);
        Date expiryDate = calendar.getTime();
        calendar.set(2021, Calendar.MARCH, 7);
        Date timeLeft = calendar.getTime();
        Food milk = new Milk("Сыр", expiryDate, createDate, 150.0, 0.0);
        Food milkTwo = new Milk("Сыр", expiryDate, createDate, 150.0, 0.0);
        Food milkThree = new Milk("Сыр", expiryDate, createDate, 150.0, 0.0);
        controllQuality.checkFood(milk, timeLeft);
        controllQuality.checkFood(milkTwo, timeLeft);
        controllQuality.checkFood(milkThree, timeLeft);
        assertEquals(controllQuality.sizeWarehouse(), Integer.valueOf(0));
        assertEquals(controllQuality.sizeShop(), Integer.valueOf(3));
        assertEquals(controllQuality.sizeTrash(), Integer.valueOf(0));
    }

    @Test
    public void whenAllInShopWithDisc() {
        ControllQuality controllQuality = new ControllQuality();
        Calendar calendar = new GregorianCalendar(2021, Calendar.MARCH, 0);
        Date createDate = calendar.getTime();
        calendar.set(2021, Calendar.MARCH, 15);
        Date expiryDate = calendar.getTime();
        calendar.set(2021, Calendar.MARCH, 14);
        Date timeLeft = calendar.getTime();
        Food milk = new Milk("Молоко", expiryDate, createDate, 150.0, 0.0);
        Food milkTwo = new Milk("Молоко", expiryDate, createDate, 150.0, 0.0);
        Food milkThree = new Milk("Молоко", expiryDate, createDate, 150.0, 0.0);
        controllQuality.checkFood(milk, timeLeft);
        controllQuality.checkFood(milkTwo, timeLeft);
        controllQuality.checkFood(milkThree, timeLeft);
        assertEquals(controllQuality.sizeWarehouse(), Integer.valueOf(0));
        assertEquals(controllQuality.sizeShop(), Integer.valueOf(3));
        assertEquals(controllQuality.sizeTrash(), Integer.valueOf(0));
    }

    @Test
    public void whenAllInTrash() {
        ControllQuality controllQuality = new ControllQuality();
        Calendar calendar = new GregorianCalendar(2021, Calendar.MARCH, 0);
        Date createDate = calendar.getTime();
        calendar.set(2021, Calendar.MARCH, 15);
        Date expiryDate = calendar.getTime();
        calendar.set(2021, Calendar.MARCH, 15);
        Date timeLeft = calendar.getTime();
        Food bread = new Milk("Хлеб", expiryDate, createDate, 150.0, 0.0);
        Food breadTwo = new Milk("Хлеб", expiryDate, createDate, 150.0, 0.0);
        Food breadThree = new Milk("Хлеб", expiryDate, createDate, 150.0, 0.0);
        controllQuality.checkFood(bread, timeLeft);
        controllQuality.checkFood(breadTwo, timeLeft);
        controllQuality.checkFood(breadThree, timeLeft);
        assertEquals(controllQuality.sizeWarehouse(), Integer.valueOf(0));
        assertEquals(controllQuality.sizeShop(), Integer.valueOf(0));
        assertEquals(controllQuality.sizeTrash(), Integer.valueOf(3));
    }
}