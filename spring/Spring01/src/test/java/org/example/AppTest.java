package org.example;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
  public void test01(){
//        获取容器对象
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
//        ApplicationContext applicationContext =
//                new FileSystemXmlApplicationContext("/Users/ywh/CODESRC/srclearning/spring/Spring01/src/main/resources/applicationContext.xml");
//        从容器对象中获取bean对象
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);
  }
}
