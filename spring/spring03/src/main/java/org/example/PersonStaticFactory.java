package org.example;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/11 10:15
 */
public class PersonStaticFactory {
    public PersonStaticFactory() {
        System.out.println("PersonStaticFactory constructor.........");
    }

    public static Person newPersonIns(String pid, String name, int age){
//        "2","公孙离",24
        System.out.println("PersonStaticFactory........");
        return new Person(pid,name,age);
    }
}
