import com.example.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {
    @Test
    public void test01(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object o = applicationContext.getBean("person");
        Person p = applicationContext.getBean(Person.class);
        System.out.println(o.toString());
        System.out.println(p.toString());
    }
}
