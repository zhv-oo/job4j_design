package ru.job4j.lsp;

import java.util.Date;
/**
 * \* User: zhv
 * \* Date: 21.11.2021
 * \* Time: 18:11
 * \* Description: Класс продукта.
 * \
 */
public class Cheese extends Food {

    public Cheese(String name, Date expiryDate, Date createDate, Double price, Double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}