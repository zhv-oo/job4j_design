package ru.job4j.lsp;


import java.util.ArrayList;
import java.util.List;
/**
 * \* User: zhv
 * \* Date: 21.11.2021
 * \* Time: 18:11
 * \* Description: Класс хранилище для продуктов срок годности которых больше меньше 75% но больше 0.
 * \
 */
public class Shop implements Storage {
    private List<Food> shop;

    public Shop() {
        this.shop = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        shop.add(food);
    }

    public Integer getSize() {
        return this.shop.size();
    }
}