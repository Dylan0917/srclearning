package com.example;

import org.springframework.stereotype.Component;

@Component(value = "car1")
public class Car {
    private String cid;
    private String color;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "cid='" + cid + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
