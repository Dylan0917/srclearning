import org.example.ApplicationContext;
import org.example.ClassPathXmlApplicationContext;
import org.example.Person;
import org.junit.Test;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/9 11:09
 */
public class AppTest {
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person p = context.getBean(Person.class);
        System.out.println(p);
    }
}
