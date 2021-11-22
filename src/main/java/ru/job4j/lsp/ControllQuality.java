package ru.job4j.lsp;

import java.util.List;

/**
 * \* User: zhv
 * \* Date: 22.11.2021
 * \* Time: 19:30
 * \* Description: Класс контроля и распределения продуктов по складам.
 * \
 */
public class ControllQuality {
    List<Storage> storage;

    public ControllQuality(List<Storage> storage) {
        this.storage = storage;
    }

    public void distribute(Food food) {
        for (Storage store : storage) {
            if (store.accept(food)) {
                store.add(food);
            }
        }
    }
}