package org.example;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/9 10:58
 */
public class Person {
    private String pid;
    private String name;
    private Car car;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", car=" + car +
                '}';
    }
}
