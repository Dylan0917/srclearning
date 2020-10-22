package com.example;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 切面lei
 */
@Component
@Aspect //表示是一个切面类对象
public class LogAspect {

    @Before(value = "execution(public int com.example.CaculatorImpl.*(int, int))")
    public void beforeLog(){
        System.out.println("在目标方法前执行。。");
    }
    @After(value = "execution(public int com.example.CaculatorImpl.*(int, int))")
    public void afterLog(){
        System.out.println("在目标方法后执行。。");
    }


}
