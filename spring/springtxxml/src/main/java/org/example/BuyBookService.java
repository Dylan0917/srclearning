package org.example;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;


public class BuyBookService {

    JdbcTemplate jdbcTemplate;

    public void insetBook(){
        String sql = "insert into book_stock values (?,?)";
        List<Object[]> para = new ArrayList<>();
        para.add(new Object[]{"isbn-1",100});
        para.add(new Object[]{"isbn-2",100});
        para.add(new Object[]{"isbn-3",100});
        para.add(new Object[]{"isbn-4",100});
        int[] ret = jdbcTemplate.batchUpdate(sql,para);
        System.out.println(ret);
    }
    public void insertUser(){
        String sql = "insert into users values (?,?)";
        List<Object[]> para = new ArrayList<>();
        para.add(new Object[]{"person1",1000});
        para.add(new Object[]{"person2",1000});
        int[] ret = jdbcTemplate.batchUpdate(sql,para);
        System.out.println(ret);
    }
    public void insertPrice(){
        String sql = "insert into book_price values (?,?)";
        List<Object[]> para = new ArrayList<>();
        para.add(new Object[]{"isbn-1",10});
        para.add(new Object[]{"isbn-2",10});
        para.add(new Object[]{"isbn-3",10});
        para.add(new Object[]{"isbn-4",10});
        int[] ret = jdbcTemplate.batchUpdate(sql,para);
        System.out.println(ret);
    }

    /**
     * @Transactional(propagation = Propagation.SUPPORTS) 外层没有事务 所有不会开启事务
     */
//    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public void buyBooks(){
        String sql = "update users set money=money-? where user_name=?";
        String sql1 = "update book_stock set stock=stock-1 where book_id=?";
        jdbcTemplate.update(sql,10,"person1");
        jdbcTemplate.update(sql1,"isbn-1");
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
