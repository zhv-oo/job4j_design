package ru.job4j.lsp;

import java.util.Date;
/**
 * \* User: zhv
 * \* Date: 21.11.2021
 * \* Time: 18:11
 * \* Description: Класс контроля и распределения продуктов по складам.
 * \
 */
public class ControllQuality {
    Storage storage;
    Storage warehouse = new Warehouse();
    Storage shop = new Shop();
    Storage trash = new Trash();

    private void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void checkFood(Food food, Date nowDate) {
        int chk = getPercent(food.expiry(), nowDate, food.getCreateDate());
        if (chk >= 75) {
            setStorage(warehouse);
            storage.add(food);
        } else if (chk > 25) {
            setStorage(shop);
            storage.add(food);
        } else if (chk < 25 && chk > 0) {
            food.setDiscount(25.0);
            setStorage(shop);
            storage.add(food);
        } else if (chk <= 0) {
            setStorage(trash);
            storage.add(food);
        }
    }

    public Integer sizeWarehouse() {
        setStorage(warehouse);
        return storage.getSize();
    }

    public Integer sizeShop() {
        setStorage(shop);
        return storage.getSize();
    }

    public Integer sizeTrash() {
        setStorage(trash);
        return storage.getSize();
    }

    private static Integer getPercent(Long exp, Date nwDate, Date create) {
        return 100 - Math.toIntExact((nwDate.getTime() - create.getTime()) * 100 / exp);
    }
}
