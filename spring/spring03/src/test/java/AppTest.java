import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/11 9:57
 */
public class AppTest {

    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Object o4 = context.getBean("person03");
//        Object o5 = context.getBean("person04");
//        Object o2 = context.getBean("person02");
//        Object o3 = context.getBean("person03","34","fddfdf","43");
//        System.out.println(o4);
//        System.out.println(o5);
//        System.out.println(o2);
//        System.out.println(o3);
    }


}
