package com.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面lei
 */
@Component
@Aspect //表示是一个切面类对象
public class LogAspect {
    // 抽取公共的切入点
    @Pointcut("execution(* com.example..*.*(..))")
    public void pointCut() {}

    @Before(value = "pointCut()")
    public void beforeLog(){
        System.out.println("在目标方法前执行。。");
    }
//    @After(value = "execution(public int com.example.CaculatorImpl.*(int, int))")
    @After(value = "pointCut()")
    public void afterLog(){
        System.out.println("在目标方法后执行。。");
    }
   @AfterReturning(value = "pointCut()")
    public void returnLog(){
        System.out.println("正常返回通知。。");
    }
    //异常返回通知
    @AfterThrowing(value = "pointCut()")
    public void returnErrorLog(){
        System.out.println("异常返回通知。。");
    }
    //环绕通知
    @Around(value = "pointCut()")
    public void aroundLog(ProceedingJoinPoint point) throws Throwable {
        String methodname = point.getSignature().getName();
        System.out.println("环绕通知，执行"+methodname+"方法前");
        point.proceed();
        System.out.println("环绕通知，执行"+methodname+"方法后");
    }
    /*环绕通知，执行add1方法前
    在目标方法前执行。。
            3
            ----------add1执行完毕----------
    正常返回通知。。
    在目标方法后执行。。
    环绕通知，执行add1方法后*/



}
