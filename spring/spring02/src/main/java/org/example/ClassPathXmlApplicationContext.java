package org.example;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/9 9:45
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    private final Map<String, Object> nameObjects = new HashMap();
    private final Map<String, Object> classObjects = new HashMap();

    public ClassPathXmlApplicationContext(String xmlPath) {
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResource(xmlPath).openStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> beans = rootElement.elements();
            for (Element beanEle:beans
                 ) {
                String className = beanEle.attributeValue("class");
                String id = beanEle.attributeValue("id");
                Class cls = Class.forName(className);
                Object o = cls.newInstance();
                nameObjects.put(id, o);
                classObjects.put(cls.getSimpleName(),o);
                List<Element> properties = beanEle.elements();
                for (Element ele:properties
                     ) {
                    String name = ele.attributeValue("name");
                    String ref = ele.attributeValue("ref");
                    Object o2 = nameObjects.get(ref);
                    String methodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
                    Method method = cls.getMethod(methodName,o2.getClass());
                    method.invoke(o,o2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public <T> T getBean(Class<T> tClass) {
        return (T)classObjects.get(tClass.getSimpleName());
    }

    @Override
    public Object getBean(String beanId) {
        return nameObjects.get(beanId);
    }
}
