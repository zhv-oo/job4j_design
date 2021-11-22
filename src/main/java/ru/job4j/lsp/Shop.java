package ru.job4j.lsp;


import java.util.ArrayList;
import java.util.List;
/**
 * \* User: zhv
 * \* Date: 22.11.2021
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
    public boolean add(Food food) {
        double percent = new Shop().getExpirationPercent(food);
        boolean res = accept(food);
        boolean setDiscount = percent <= 25 && percent > 0;
        if (setDiscount) {
            food.setDiscount(25.0);
        }
        shop.add(food);
        return res;
    }

    @Override
    public boolean accept(Food food) {
        double res = new Shop().getExpirationPercent(food);
        return 75.0 > res && res > 0.0;
    }

    public Integer getSize() {
        return this.shop.size();
    }
}