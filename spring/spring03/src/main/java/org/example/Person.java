package org.example;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/11 9:55
 */
public class Person {
    private String pid;
    private String name;
    private int age;


    public Person() {
        System.out.println("Person no param。。。。。");
    }

    public Person(String pid, String name, int age) {
        System.out.println("Person have param。。。。。");
        this.pid = pid;
        this.name = name;
        this.age = age;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
