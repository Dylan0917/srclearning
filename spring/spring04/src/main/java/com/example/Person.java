package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope
public class Person {
    @Value(value = "23")
    private String pid;

    @Value(value = "nnnnn")
    private String name;

    @Autowired
    @Qualifier(value = "car1")//指定id
    private Car car;


    //初始化方法
    @PostConstruct
    public void initmethod(){
        System.out.println("Person init....");
    }
    //销毁方法
    @PreDestroy
    public void destoryMethod(){
        System.out.println("Person destory....");
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

    @Override
    public String toString() {
        return "Person{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", car=" + car +
                '}';
    }
}
