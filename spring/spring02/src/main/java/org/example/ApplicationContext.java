package org.example;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/9 9:42
 */
public interface ApplicationContext {

    public<T> T getBean(Class<T> tClass);

    public Object getBean(String beanId);

}
