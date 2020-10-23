package com.example;

import org.springframework.stereotype.Component;

@Component
public class CaculatorImpl  implements Caculator{
    @Override
    public int add(int a, int b) {

        int sum = a + b;
        System.out.println("----------add执行完毕----------");
        return sum;
    }

    @Override
    public void add1(int a, int b) {
        int sum = a + b;
        System.out.println(sum);
        System.out.println("----------add1执行完毕----------");
    }

    @Override
    public void divide(int a, int b) {
        float c = a/b;
        System.out.println("----------divide执行完毕----------");
    }
}
