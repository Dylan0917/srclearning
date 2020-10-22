package com.example;

import org.springframework.stereotype.Component;

@Component
public class CaculatorImpl  implements Caculator{
    @Override
    public int add(int a, int b) {

        int sum = a + b;

        return sum;
    }
}
