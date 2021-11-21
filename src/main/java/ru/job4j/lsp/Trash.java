package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;
/**
 * \* User: zhv
 * \* Date: 21.11.2021
 * \* Time: 18:11
 * \* Description: Класс хранилище для продуктов с истекшим сроком годности.
 * \
 */
public class Trash implements Storage {
    private final List<Food> trash;

    public Trash() {
        this.trash = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        trash.add(food);
    }

    public Integer getSize() {
        return this.trash.size();
    }
}