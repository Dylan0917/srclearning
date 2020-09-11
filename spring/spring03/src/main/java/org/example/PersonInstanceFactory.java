package org.example;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/11 10:36
 */
public class PersonInstanceFactory {
    public PersonInstanceFactory() {
        System.out.println("PersonInstanceFactory constructor.........");
    }

    public Person newIns(String pid, String name, int age){
        System.out.println("PersonInstanceFactory......");
        return new Person(pid,name,age);
    }
}
