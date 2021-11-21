package ru.job4j.lsp;

import java.util.Date;

/**
 * \* User: zhv
 * \* Date: 21.11.2021
 * \* Time: 18:11
 * \* Description: Базовый класс для еды.
 * \
 */

public class Food {
    private String name;
    private final Date expiryDate;
    private final Date createDate;
    private Double price;
    private Double discount;

    public Food(String name, Date expiryDate, Date createDate, Double price, Double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * Метод возвращает время срока годности в Long.
     * @return отрезок время хранения.
     */
    public Long expiry() {
        return this.getExpiryDate().getTime() - this.getCreateDate().getTime();
    }
}