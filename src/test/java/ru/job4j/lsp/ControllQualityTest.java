package ru.job4j.lsp;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class ControllQualityTest {
    Storage warehouse = new Warehouse();
    Storage shop = new Shop();
    Storage trash = new Trash();
    ControllQuality controllQuality = new ControllQuality(List.of(warehouse, shop, trash));
    @Test
    public void whenAllInWarehouse() {

        Calendar calendar = new GregorianCalendar(2021, Calendar.NOVEMBER, 20);
        Date createDate = calendar.getTime();
        calendar.set(2021, Calendar.DECEMBER, 19);
        Date expiryDate = calendar.getTime();
        Food cheese = new Cheese("Сыр", expiryDate, createDate, 150.0, 0.0);
        Food cheeseTwo = new Cheese("Сыр", expiryDate, createDate, 150.0, 0.0);
        Food cheeseThree = new Cheese("Сыр", expiryDate, createDate, 150.0, 0.0);
        controllQuality.distribute(cheese);
        controllQuality.distribute(cheeseTwo);
        controllQuality.distribute(cheeseThree);
        assertEquals(warehouse.getSize(), Integer.valueOf(3));
        assertEquals(shop.getSize(), Integer.valueOf(0));
        assertEquals(trash.getSize(), Integer.valueOf(0));
    }

    @Test
    public void whenAllInShop() {
        Calendar calendar = new GregorianCalendar(2021, Calendar.NOVEMBER, 12);
        Date createDate = calendar.getTime();
        calendar.set(2021, Calendar.NOVEMBER, 26);
        Date expiryDate = calendar.getTime();
        Food milk = new Milk("Сыр", expiryDate, createDate, 150.0, 0.0);
        Food milkTwo = new Milk("Сыр", expiryDate, createDate, 150.0, 0.0);
        Food milkThree = new Milk("Сыр", expiryDate, createDate, 150.0, 0.0);
        controllQuality.distribute(milk);
        controllQuality.distribute(milkTwo);
        controllQuality.distribute(milkThree);
        assertEquals(warehouse.getSize(), Integer.valueOf(0));
        assertEquals(shop.getSize(), Integer.valueOf(3));
        assertEquals(trash.getSize(), Integer.valueOf(0));
        assertEquals(milk.getDiscount(), Double.valueOf(0));
    }

    @Test
    public void whenAllInShopWithDisc() {
        Calendar calendar = new GregorianCalendar(2021, Calendar.NOVEMBER, 12);
        Date createDate = calendar.getTime();
        calendar.set(2021, Calendar.NOVEMBER, 23);
        Date expiryDate = calendar.getTime();
        Food milk = new Milk("Молоко", expiryDate, createDate, 150.0, 0.0);
        Food milkTwo = new Milk("Молоко", expiryDate, createDate, 150.0, 0.0);
        Food milkThree = new Milk("Молоко", expiryDate, createDate, 150.0, 0.0);
        controllQuality.distribute(milk);
        controllQuality.distribute(milkTwo);
        controllQuality.distribute(milkThree);
        assertEquals(warehouse.getSize(), Integer.valueOf(0));
        assertEquals(shop.getSize(), Integer.valueOf(3));
        assertEquals(trash.getSize(), Integer.valueOf(0));
        assertEquals(milk.getDiscount(), Double.valueOf(25));
    }

    @Test
    public void whenAllInTrash() {
        Calendar calendar = new GregorianCalendar(2021, Calendar.NOVEMBER, 12);
        Date createDate = calendar.getTime();
        calendar.set(2021, Calendar.NOVEMBER, 22);
        Date expiryDate = calendar.getTime();
        Food bread = new Milk("Хлеб", expiryDate, createDate, 150.0, 0.0);
        Food breadTwo = new Milk("Хлеб", expiryDate, createDate, 150.0, 0.0);
        Food breadThree = new Milk("Хлеб", expiryDate, createDate, 150.0, 0.0);
        controllQuality.distribute(bread);
        controllQuality.distribute(breadTwo);
        controllQuality.distribute(breadThree);
        assertEquals(warehouse.getSize(), Integer.valueOf(0));
        assertEquals(shop.getSize(), Integer.valueOf(0));
        assertEquals(trash.getSize(), Integer.valueOf(3));
    }
}