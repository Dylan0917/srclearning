package org.example;

public class Person {
    private int id;
    private String name;
    private boolean gender;
    private double salary;
    private Car car;

    public Person() {
        System.out.println("Person creating...");
    }
    public void initMethod(){
        System.out.println("Person init...");
    }
    public void destoryMethod(){
        System.out.println("Person destory...");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", salary=" + salary +
                '}';
    }
}
