package org.example;

import java.math.BigDecimal;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/8 9:33
 */
public class Car {
    private String color;
    private BigDecimal price;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
