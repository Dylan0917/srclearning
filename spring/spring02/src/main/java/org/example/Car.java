package org.example;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/9 11:05
 */
public class Car {
    private String cid;
    private String carName;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "cid='" + cid + '\'' +
                ", carName='" + carName + '\'' +
                '}';
    }
}
