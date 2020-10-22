import com.example.Caculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {
    @Test
    public void test01(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Caculator o = (Caculator) context.getBean(Caculator.class);
        int s = o.add(1,2);
        System.out.println(s);

    }
}
