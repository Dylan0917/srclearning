package org.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations = "classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest 
{
    @Autowired
    Service1 s;

    @Autowired
    BuyBookService buyBookService;

    @Test
    public void test01(){
        s.testzj01();
        System.out.println("----test01------");
    }
    @Test
    public void test02(){
        buyBookService.insertUser();
        System.out.println("----test01------");
    }
    @Test
    public void test03(){
        buyBookService.insetBook();
    }
    @Test
    public void test04(){
        buyBookService.insertPrice();
    }
    @Test
    public void test05(){
        buyBookService.buyBooks();
    }




}
