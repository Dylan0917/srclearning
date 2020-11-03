package org.example;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring.xml");
        BuyBookService bookService = context.getBean(BuyBookService.class);
        bookService.buyBooks();
    }
}
