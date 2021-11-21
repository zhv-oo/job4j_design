package ru.job4j.lsp;


import java.util.ArrayList;
import java.util.List;
/**
 * \* User: zhv
 * \* Date: 21.11.2021
 * \* Time: 18:11
 * \* Description: Класс хранилище для продуктов срок годности которых больше 75%.
 * \
 */
public class Warehouse implements Storage {
    private final List<Food> warehouse;

    public Warehouse() {
        this.warehouse = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        warehouse.add(food);
    }

    public Integer getSize() {
        return this.warehouse.size();
    }
}